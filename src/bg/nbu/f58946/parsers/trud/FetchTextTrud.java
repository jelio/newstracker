package bg.nbu.f58946.parsers.trud;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bg.nbu.f58946.bo.ALL_SITES;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.AFetchText;

public class FetchTextTrud extends AFetchText {
	public FetchTextTrud(ALL_SITES site) {
		super(site);
		// TODO Auto-generated constructor stub
	}

	private static final String className = "article-content";

	@Override
	public String getArticleText(Document doc) throws BusinessException {
		Elements matchedArticles = doc.getElementsByClass(className);
		if (matchedArticles.size() > 0) {
			return matchedArticles.get(0).text();
		}
		throw new BusinessException();
	}

	@Override
	public String getTitle(Document doc) throws BusinessException {
		Elements matchedArticles = doc.select("div.title-links > h1");

		if (matchedArticles.size() > 0) {
			return matchedArticles.get(0).ownText();
		}
		throw new BusinessException();
	}

	@Override
	public Elements getElements(Document document) {
		return document.select(".category-content-box > a");
	}

	@Override
	public String getHref(Element e) {
		return e.attr("href");
	}
}
