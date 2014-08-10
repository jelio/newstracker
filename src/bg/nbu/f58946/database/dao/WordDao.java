package bg.nbu.f58946.database.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.nbu.f58946.bo.Article;
import bg.nbu.f58946.bo.Word;
import bg.nbu.f58946.database.MyDataSource;
import bg.nbu.f58946.exceptions.BusinessException;

public class WordDao {
	final static Logger logger = LoggerFactory.getLogger(WordDao.class);
	private Word word;

	public WordDao(Word word) {
		this.word = word;
	}

	public static void saveWords(ArrayList<String> words) {
		for (String word : words) {

		}
	}

	public boolean save() {
		return true ; 
	}
	
	
	public static Map<String, Word> loadWords() {

		Map<String, Word> allWords = Collections
				.synchronizedMap(new HashMap<String, Word>());

		String query = "SELECT * FROM words";

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

				allWords.put(iWord.getWord(), iWord);
			}

			connection.close();

		} catch (SQLException | BusinessException e) {
			logger.error(e.toString());
		}
		return allWords;
	}
}
