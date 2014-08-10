package bg.nbu.f58946.jobs.dnevnik;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.nbu.f58946.bo.Article;
import bg.nbu.f58946.database.dao.ArticleDao;
import bg.nbu.f58946.utils.Utils;

public class HandleArticle implements Runnable {

	final static Logger logger = LoggerFactory.getLogger(HandleArticle.class);

	public HandleArticle() {
	}

	@Override
	public void run() {
		try {
			ArrayList<Article> unprocessedArticles = ArticleDao
					.getUnprocessed();

			for (Article a : unprocessedArticles) {
				handle(a);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	private void handle(Article a){
		logger.debug("article : {}", a);
		
		ArrayList<String> titleArray = Utils.toWords(a.getTitle()) ;
		
		ArticleDao.setProcessed(a.getId());		
	}
}
