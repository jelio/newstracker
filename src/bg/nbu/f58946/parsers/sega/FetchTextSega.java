package bg.nbu.f58946.parsers.sega;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bg.nbu.f58946.bo.Site;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.AFetchText;

public class FetchTextSega extends AFetchText {
	public FetchTextSega(Site site) {
		super(site);
		// TODO Auto-generated constructor stub
	}

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
