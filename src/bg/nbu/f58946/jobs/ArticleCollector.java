package bg.nbu.f58946.jobs;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.nbu.f58946.bo.Article;
import bg.nbu.f58946.bo.Site;
import bg.nbu.f58946.database.dao.ArticleDao;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.main.Main;
import bg.nbu.f58946.parsers.Feeders;
import bg.nbu.f58946.parsers.FetchTextFactory;
import bg.nbu.f58946.parsers.IFetchText;

public class ArticleCollector implements Runnable {
	final static Logger logger = LoggerFactory
			.getLogger(ArticleCollector.class);

	private boolean isRunning = true;

	@Override
	public void run() {
		while (isRunning) {

			for (Site site : Main.allowedSites) {
				logger.debug("Collection articles for site : {} ", site);

				IFetchText fetcher = FetchTextFactory
						.getFetcher(site);

				// new HashMap<String, Article>();
				Map<String, Article> myArticles = null;
				try {
					myArticles = fetcher.getArticles();
				} catch (BusinessException e) {
					logger.error(e.toString());
				}

				saveArticles(myArticles);

				break;

			}

			isRunning = false;

			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				logger.error(e.toString());
			}

		}
	}

	private void saveArticles(Map<String, Article> articles) {

		for (Article art : articles.values()) {
			ArticleDao aDo = new ArticleDao(art);
			try {
				logger.trace("Recording article : {}", art);
				aDo.save();
			} catch (BusinessException e) {
				logger.error("{}", e);
			}
		}
	}
}
