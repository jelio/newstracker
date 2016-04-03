package bg.nbu.f58946.parsers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.nbu.f58946.bo.ALL_SITES;
import bg.nbu.f58946.bo.Article;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.utils.MyUtils;

public abstract class AFetchText implements IFetchText {

	private ALL_SITES site;

	final static Logger logger = LoggerFactory.getLogger(AFetchText.class);

	public AFetchText(ALL_SITES site2) {
		this.setSite(site2);
	}

	@Override
	public Map<String, Article> getArticles() throws BusinessException {

		Map<String, Article> articles = new HashMap<String, Article>();
		Document document = null;

		try {
			document = MyUtils.fetchDocument(getSite().getAllArticlesHref());
		} catch (IOException e) {
			logger.error("Cannot fetch for {}", getSite().getAllArticlesHref());
			logger.error(e.toString());
			throw new BusinessException();
		}

		// call overridden method getElements for each site
		Elements elements = getElements(document);

		for (Element element : elements) {

			// call overridden method getHref for each site
			String href = getHref(element);

			//
			// TODO parse url and filter it .. call fetcher method filterURI
			Document doc ;
			try {
				doc = MyUtils.fetchDocument(getSite().getHref() + href);
			} catch (Exception e) {
				logger.error("Cannot fetch : " + getSite().getHref() + href);
				continue;
			}			

			String text;
			String title;

			try {
				// call overridden method getArticleText for each site
				text = getArticleText(doc);
				// call overridden method getTitle for each site
				title = getTitle(doc);

			} catch (BusinessException e) {
				logger.warn("Error at {}", getSite().getHref() + href);
				continue;
			}

			Article content = new Article();

			// @formatter:off
			content.setContent(text).setMd5Href(MyUtils.getMD5(getSite().getHref() + href))
					.setHref(getSite().getHref() + href).setMd5Content(MyUtils.getMD5(text)).setTitle(title);
			// @formatter:on

			articles.put(MyUtils.getMD5(getSite().getHref() + href), content);
		}

		return articles;
	}

	/**
	 * @return the site
	 */
	public ALL_SITES getSite() {
		return site;
	}

	/**
	 * @param site2
	 *            the site to set
	 */
	public void setSite(ALL_SITES site2) {
		this.site = site2;
	}
}
