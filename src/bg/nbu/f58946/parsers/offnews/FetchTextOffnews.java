package bg.nbu.f58946.parsers.offnews;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.IFetchText;

public class FetchTextOffnews implements IFetchText {
	private static final String className = "news_text";

	@Override
	public String getArticleText(Document doc) throws BusinessException {
		Elements matchedArticles = doc.getElementsByClass(className);

		if (matchedArticles.size() > 0) {
			Element element = matchedArticles.get(0);
			for (Element el : element.getAllElements()) {
				if(el.className().equalsIgnoreCase("similar_news_box")) {
					el.remove();					
				}				
			}
			
			System.out.println(element.text());
			return matchedArticles.get(0).ownText();
		}
		throw new BusinessException();
	}

}