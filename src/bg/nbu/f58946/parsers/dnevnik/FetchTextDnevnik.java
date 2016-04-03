package bg.nbu.f58946.parsers.dnevnik;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.nbu.f58946.bo.ALL_SITES;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.AFetchText;

public class FetchTextDnevnik extends AFetchText {
	private static final String className = "article";
	private static final String titleClassName = "aritcleHead";
	final static Logger logger = LoggerFactory
			.getLogger(FetchTextDnevnik.class);

	public FetchTextDnevnik(ALL_SITES site) {
		super(site);
	}
	/**
	 * takes all content in div.article
	 */
	@Override	
	public String getArticleText(Document doc) throws BusinessException {
		Elements matchedArticles = doc.select("div." + className);

		if (matchedArticles.size() > 0) {
			return matchedArticles.get(0).ownText();
		}
		throw new BusinessException();
	}

	public String getTitle(Document doc) throws BusinessException {

		Elements matchedArticles = doc
				.select("div." + titleClassName + " > h2");

		if (matchedArticles.size() > 0) {
			return matchedArticles.get(0).ownText();
		}
		throw new BusinessException();

	}

	@Override
	public Elements getElements(Document document) {
		return document.select(".info > a");
	}

	@Override
	public String getHref(Element e) {
		return e.attr("href") ; 
	}
	
}
