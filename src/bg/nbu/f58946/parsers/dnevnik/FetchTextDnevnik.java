package bg.nbu.f58946.parsers.dnevnik;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.IFetchText;

public class FetchTextDnevnik implements IFetchText {
	private static final String className = "article";
	private static final String titleClassName = "aritcleHead";

	@Override
	public String getArticleText(Document doc) throws BusinessException {
		Elements matchedArticles = doc.select("div." + className);

		if (matchedArticles.size() > 0) {
			return matchedArticles.get(0).ownText();
		}		
		throw new BusinessException();
	}
	
	public String getTitle(Document doc) throws BusinessException {
		
		Elements matchedArticles = doc.select("div." + titleClassName + " > h2");

		if (matchedArticles.size() > 0) {
			return matchedArticles.get(0).ownText();
		}
		throw new BusinessException();
		
	}

}
