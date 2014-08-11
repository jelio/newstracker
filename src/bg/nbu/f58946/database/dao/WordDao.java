package bg.nbu.f58946.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.nbu.f58946.bo.Word;
import bg.nbu.f58946.database.MyDataSource;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.main.Main;

public class WordDao {
	final static Logger logger = LoggerFactory.getLogger(WordDao.class);
	private Word word;

	public WordDao(Word word) {
		this.word = word;
	}

	/**
	 * Save in DB all words TODO Update General Map with all words/ids
	 * 
	 * @param words
	 */
	public static void saveWords(ArrayList<String> words) {
		for (String w : words) {
			WordDao iWordDao = new WordDao((new Word()).setWord(w));
			try {
				iWordDao.saveSimple();
			} catch (BusinessException e) {
				logger.error("Cannot save word : {}, error : {}", w,
						e.toString());
			}
			break;
		}
	}

	public boolean saveSimple() throws BusinessException {
		try {

			Connection con = MyDataSource.getInstance().getConnection();

			String sql = "insert into words (word) values (?)";

			PreparedStatement preparedStament = con.prepareStatement(sql,
					Statement.RETURN_GENERATED_KEYS);

			preparedStament.setString(1, word.getWord());

			preparedStament.executeUpdate(); // return number of affected rows

			int id = -1;
			ResultSet rs = preparedStament.getGeneratedKeys();
			if (rs.next()) {
				id = rs.getInt(1);
			}

			if (id < 1) {
				logger.error("Cannot fetch id");
				return false;
			}

			logger.debug("Insert word : {} with id : {}", word.getWord(), id);
		}

		catch (SQLException e) {
			logger.error(e.toString());
			return false;
		}

		return true;
	}

	public static void loadAllWords() {

		String query = "SELECT * FROM words WHERE lang_id = 1";

		try {

			Connection connection = MyDataSource.getInstance().getConnection();

			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {

				Word iWord = new Word();
				iWord.setId(resultSet.getInt(1));
				iWord.setParentId(resultSet.getInt(2));
				iWord.setWord(resultSet.getString(3));
				iWord.setIsName(resultSet.getInt(4));
				iWord.setIsSmallWord(resultSet.getInt(5));
				iWord.setLangId(resultSet.getInt(6));

				logger.trace("fetch article : {} ", iWord);

				// Load words in Main::wordsDictionary map 
				Main.wordsDictionary.put(iWord.getWord(), iWord);
			}

			connection.close();

		} catch (SQLException | BusinessException e) {
			logger.error(e.toString());
		}
	}
}
