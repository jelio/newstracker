package bg.nbu.f58946.parsers.dir;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bg.nbu.f58946.bo.ALL_SITES;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.AFetchText;

public class FetchTextDir extends AFetchText {
	public FetchTextDir(ALL_SITES site) {
		super(site);
		// TODO Auto-generated constructor stub
	}

	private static final String className = "textt";

	@Override
	public String getArticleText(Document doc) throws BusinessException {
		Elements matchedArticles = doc.select("." + className);
		if (matchedArticles.size() > 0) {
			return matchedArticles.get(0).text();
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
