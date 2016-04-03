package bg.nbu.f58946.parsers.offnews;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bg.nbu.f58946.bo.ALL_SITES;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.AFetchText;

public class FetchTextOffnews extends AFetchText {
	
	public FetchTextOffnews(ALL_SITES site) {
		super(site);
	}

	private static final String className = "news_text";

	@Override
	public String getArticleText(Document doc) throws BusinessException {
		Elements matchedArticles = doc.select("div." + className);

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
	
	@Override
	public String getTitle(Document doc) throws BusinessException {
		return "";
	}

	@Override
	public Elements getElements(Document document) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getHref(Element e) {
		// TODO Auto-generated method stub
		return null;
	}
}
