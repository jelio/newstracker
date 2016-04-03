package bg.nbu.f58946.utils;

import java.util.HashMap;
import java.util.Map;

import bg.nbu.f58946.bo.ALL_SITES;
import bg.nbu.f58946.bo.Article;

public class TempUtil {

	public static Article createTestArticle() {
		Article article = new Article();
		article.setAuthor("jelio");
		article.setSiteId(ALL_SITES.DNEVNIK.toString());
		article.setContent("This is a very very long text");
		article.setMd5Content(MyUtils.getMD5(article.getContent()));
		article.setHref("https://pokerstrategy.com/home/");
		article.setMd5Href(MyUtils.getMD5(article.getHref()));

		return article;
	}
	

}
 