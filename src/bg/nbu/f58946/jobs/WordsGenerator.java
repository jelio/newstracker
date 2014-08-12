package bg.nbu.f58946.jobs;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.nbu.f58946.bo.Article;
import bg.nbu.f58946.database.dao.ArticleDao;
import bg.nbu.f58946.database.dao.WordDao;
import bg.nbu.f58946.utils.MyUtils;

public class WordsGenerator implements Runnable {
	final static Logger logger = LoggerFactory.getLogger(WordsGenerator.class);

	private boolean isRunning = true;

	@Override
	public void run() {
		while (isRunning) {
			try {
				ArrayList<Article> unprocessedArticles = ArticleDao
						.getUnprocessed();

				for (Article a : unprocessedArticles) {
					handle(a);
				}

			} catch (Exception e) {
				logger.error(e.toString());
			}

			isRunning = false ; 
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				logger.error(e.toString());
			}

		}
	}

	private void handle(Article a) {
		logger.debug("article : {}", a);

		ArrayList<String> titleArray = MyUtils.toWords(a.getTitle());

		WordDao.saveWords(titleArray);

		// ArticleDao.setProcessed(a.getId());
	}
}
