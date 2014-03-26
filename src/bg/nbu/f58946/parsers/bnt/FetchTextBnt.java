package bg.nbu.f58946.parsers.bnt;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.IFetchText;

public class FetchTextBnt implements IFetchText {
	private static final String className = "textt";

	@Override
	public String getArticleText(Document doc) throws BusinessException {
		Elements matchedArticles = doc.getElementsByClass(className);
		if (matchedArticles.size() > 0) {
			return matchedArticles.get(0).text();
		}
		throw new BusinessException();
	}

}
