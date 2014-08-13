package bg.nbu.f58946.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.nbu.f58946.bo.Site;
import bg.nbu.f58946.bo.Word;
import bg.nbu.f58946.database.dao.SiteDao;
import bg.nbu.f58946.database.dao.WordDao;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.jobs.ArticleCollector;
import bg.nbu.f58946.jobs.WordsGenerator;

import com.sun.syndication.io.FeedException;

public class Main {

	/**
	 * all words Map
	 */
	public static Map<String, Word> wordsDictionary = Collections
			.synchronizedMap(new HashMap<String, Word>());

	final static Logger logger = LoggerFactory.getLogger(Main.class);

	/**
	 * Array with sites which will e collected
	 */
	public final static ArrayList<Site> allowedSites = SiteDao.loadSites();

	public static void main(String[] args) throws IOException,
			BusinessException, IllegalArgumentException, FeedException {

		logger.debug(allowedSites.toString());

		initializeMap();
		startArticleCollector();
//		startWordsProcessing();

	}

	/**
	 * Start thread for collecting artciles
	 */
	private static void startArticleCollector() {
		new Thread(new ArticleCollector(), "TAC").start();
	}

	/**
	 * Start thread for words processing
	 */
	private static void startWordsProcessing() {
		new Thread(new WordsGenerator(), "TWG").start();
	}

	/**
	 * Initialize map with all words
	 */
	private static void initializeMap() {
		WordDao.loadAllWords();
	}
}
