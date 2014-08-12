package bg.nbu.f58946.parsers.trud;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import bg.nbu.f58946.bo.Site;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.AFetchText;
import bg.nbu.f58946.parsers.IFetchText;

public class FetchTextTrud extends AFetchText {
	public FetchTextTrud(Site site) {
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
		return "";
	}

	@Override
	public Elements getElements(Document document) {
		// TODO Auto-generated method stub
		return null;
	}
}
