package bg.nbu.f58946.parsers.sega;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.IFetchText;

public class FetchTextSega implements IFetchText {
	private static final String className = "a_content";

	@Override
	public String getArticleText(Document doc) throws BusinessException {
		Elements matchedArticles = doc.getElementsByClass(className);

		if (matchedArticles.size() > 0) {
			Element element = matchedArticles.get(0);
			for (Element el : element.getAllElements()) {
				if (el.tagName().equalsIgnoreCase("div")) {
					el.remove();
				}
			}

			return matchedArticles.get(0).ownText();
		}
		throw new BusinessException();
	}
	
	@Override
	public String getTitle(Document doc) throws BusinessException {
		return "";		
	}

}
