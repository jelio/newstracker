package bg.nbu.f58946.jobs;

import java.util.Map;

import org.elasticsearch.action.index.IndexResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.nbu.f58946.bo.ALL_SITES;
import bg.nbu.f58946.bo.Article;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.main.Main;
import bg.nbu.f58946.parsers.FetchTextFactory;
import bg.nbu.f58946.parsers.IFetchText;
import bg.nbu.f58946.utils.MyUtils;

public class ArticleCollector implements Runnable {
	final static Logger logger = LoggerFactory.getLogger(ArticleCollector.class);

	private boolean isRunning = true;

	@Override
	public void run() {
		while (isRunning) {

			for (ALL_SITES site : Main.allowedSites) {
				logger.debug("Collection articles for site : {} ", site);

				IFetchText fetcher = FetchTextFactory.getFetcher(site);

				// new HashMap<String, Article>();
				Map<String, Article> myArticles = null;
				try {
					myArticles = fetcher.getArticles();
					// logger.debug(myArticles.toString());
				} catch (BusinessException e) {
					logger.error(e.toString());
				}

				logger.debug(myArticles.toString());

				saveArticles(myArticles, site);

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

	private void saveArticles(Map<String, Article> myArticles, ALL_SITES site) {
		for (String s : myArticles.keySet()) {
			Article a = myArticles.get(s);
			Map<String, Object> m = MyUtils.articleToMap(a);
			IndexResponse response = Main.client.prepareIndex(Main.INDEX, site.toString()).setId(a.getMd5Href())
					.setSource(m).get();

			logger.debug("Article with id {} , title : {}, created {}", response.getId(), a.getTitle(),
					response.isCreated());

		}
	}
}
