package bg.nbu.f58946.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.dbcp2.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.nbu.f58946.bo.Site;
import bg.nbu.f58946.database.MyDataSource;
import bg.nbu.f58946.exceptions.BusinessException;

public class SiteDao {

	final static Logger logger = LoggerFactory.getLogger(SiteDao.class);

	public static ArrayList<Site> loadSites() {

		ArrayList<Site> sites = new ArrayList<Site>();

		// Load only non-frozen sites
		String query = "SELECT * FROM sites WHERE frozen = 0";

		Connection connection = null;

		try {
			connection = MyDataSource.getInstance().getConnection();

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Site iSite = new Site();
				iSite.setId(resultSet.getInt(1));
				iSite.setName(resultSet.getString(2));
				iSite.setHref(resultSet.getString(3));
				iSite.setAllNewsHref(resultSet.getString(4));

				logger.trace("fetch site : {} ", iSite);

				sites.add(iSite);
			}

		} catch (SQLException | BusinessException e) {
			logger.error(e.toString());
		} finally {
			Utils.closeQuietly(connection);
		}

		return sites;
	}

}
