package bg.nbu.f58946.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.ConnectionFactory;
import org.apache.commons.dbcp2.DriverManagerConnectionFactory;
import org.apache.commons.dbcp2.PoolableConnection;
import org.apache.commons.dbcp2.PoolableConnectionFactory;
import org.apache.commons.dbcp2.PoolingDataSource;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;

import bg.nbu.f58946.exceptions.BusinessException;

public class MyDataSource {

	private static MyDataSource instance = null;
	private DataSource myPool = null;

	private MyDataSource(String connectURI) {
		setupDataSource(connectURI);
	}

	public static MyDataSource getInstance(String connectURI) {
		if (instance == null) {
			instance = new MyDataSource(connectURI);
		}
		return instance;
	}

	public Connection getConnection() throws BusinessException {

		String dbUrl = "jdbc:mysql://localhost/nbu";
		String dbClass = "com.mysql.jdbc.Driver";
		String username = "root";
		String password = "jelio";
		try {

			Class.forName(dbClass);
			Connection connection = DriverManager.getConnection(dbUrl,
					username, password);

			return connection;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		throw new BusinessException();

		// return myPool.getConnection();
	}

	private void setupDataSource(String connectURI) {
		//
		// First, we'll create a ConnectionFactory that the
		// pool will use to create Connections.
		// We'll use the DriverManagerConnectionFactory,
		// using the connect string passed in the command line
		// arguments.
		//
		ConnectionFactory connectionFactory = new DriverManagerConnectionFactory(
				connectURI, null);

		//
		// Next we'll create the PoolableConnectionFactory, which wraps
		// the "real" Connections created by the ConnectionFactory with
		// the classes that implement the pooling functionality.
		//
		PoolableConnectionFactory poolableConnectionFactory = new PoolableConnectionFactory(
				connectionFactory, null);

		//
		// Now we'll need a ObjectPool that serves as the
		// actual pool of connections.
		//
		// We'll use a GenericObjectPool instance, although
		// any ObjectPool implementation will suffice.
		//
		ObjectPool<PoolableConnection> connectionPool = new GenericObjectPool<>(
				poolableConnectionFactory);

		// Set the factory's pool property to the owning pool
		poolableConnectionFactory.setPool(connectionPool);

		//
		// Finally, we create the PoolingDriver itself,
		// passing in the object pool we created.
		//
		// PoolingDataSource<PoolableConnection> dataSource = new
		// PoolingDataSource<>(
		// connectionPool);
		//
		// return dataSource;
		myPool = new PoolingDataSource<>(connectionPool);
	}
}
