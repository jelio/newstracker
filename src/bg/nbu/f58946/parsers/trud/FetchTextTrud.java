package bg.nbu.f58946.parsers.trud;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.IFetchText;

public class FetchTextTrud implements IFetchText {
	private static final String className = "article-content";

	@Override
	public String getArticleText(Document doc) throws BusinessException {
		Elements matchedArticles = doc.getElementsByClass(className);
		if (matchedArticles.size() > 0) {
			return matchedArticles.get(0).text();
		}
		throw new BusinessException();
	}
}
