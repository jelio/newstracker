package bg.nbu.f58946.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.commons.dbcp2.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.nbu.f58946.bo.Article;
import bg.nbu.f58946.database.MyDataSource;
import bg.nbu.f58946.exceptions.BusinessException;

public class ArticleDao {
	Article article;
	final static Logger logger = LoggerFactory.getLogger(ArticleDao.class);

	public ArticleDao(Article article) {
		this.article = article;
	}

	public void save() throws BusinessException {
		Connection connection = null;
		try {

			connection = MyDataSource.getInstance().getConnection();

			String sql = "insert into articles (site_id,title,content,href,md5_content,md5_href,author) values (?,?,?,?,?,?,?)";

			PreparedStatement preparedStament = connection
					.prepareStatement(sql);

			preparedStament.setInt(1, article.getSiteId());
			preparedStament.setString(2, article.getTitle());
			preparedStament.setString(3, article.getContent());
			preparedStament.setString(4, article.getHref());
			preparedStament.setString(5, article.getMd5Content());
			preparedStament.setString(6, article.getMd5Href());
			preparedStament.setString(7, article.getAuthor());

			preparedStament.executeUpdate();
			logger.debug("Insert article with href {}", article.getHref());
		}

		catch (SQLException e) {
			logger.error(e.toString());
		} finally {
			Utils.closeQuietly(connection);
		}

	}

	public static boolean setProcessed(int id) {
		Connection connection = null;
		try {

			connection = MyDataSource.getInstance().getConnection();

			String sql = "update articles set is_processed = 1 where is_processed = 0 and id = ?";

			PreparedStatement preparedStament = connection
					.prepareStatement(sql);

			preparedStament.setInt(1, id);

			preparedStament.execute();
			logger.debug("Set is_processed = 1 for article with id {}", id);
		}

		catch (SQLException | BusinessException e) {
			logger.error(e.toString());
			return false;
		} finally {
			Utils.closeQuietly(connection);
		}

		return true;
	}

	public static ArrayList<Article> getUnprocessed() {

		ArrayList<Article> unprocessed = new ArrayList<Article>();

		String query = "SELECT * FROM articles WHERE is_processed = 0 LIMIT 0,200";

		Connection connection = null;
		try {

			connection = MyDataSource.getInstance().getConnection();

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				Article iArticle = new Article();
				iArticle.setId(resultSet.getInt(1));
				iArticle.setSiteId(resultSet.getInt(2));
				iArticle.setTitle(resultSet.getString(3));
				iArticle.setContent(resultSet.getString(4));
				iArticle.setHref(resultSet.getString(5));
				iArticle.setMd5Content(resultSet.getString(6));
				iArticle.setMd5Href(resultSet.getString(7));
				iArticle.setAuthor(resultSet.getString(8));

				logger.trace("fetch article : {} ", iArticle);

				unprocessed.add(iArticle);
			}

		} catch (SQLException | BusinessException e) {
			logger.error(e.toString());
		} finally {
			Utils.closeQuietly(connection);
		}
		return unprocessed;
	}

}
