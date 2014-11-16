package bg.nbu.f58946.parsers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import bg.nbu.f58946.bo.Article;
import bg.nbu.f58946.bo.Site;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.http.MyHttpClient;
import bg.nbu.f58946.utils.MyUtils;

public abstract class AFetchText implements IFetchText {

	private Site site;
	private HttpClient httpClient = (new MyHttpClient()).getHttpClient();

	final static Logger logger = LoggerFactory.getLogger(AFetchText.class);

	public AFetchText(Site site) {
		this.setSite(site);
	}

	@Override
	public Map<String, Article> getArticles() throws BusinessException {

		Map<String, Article> articles = new HashMap<String, Article>();
		Document document = null;

		try {
			document = fetchDocument();
		} catch (IOException e) {
			logger.error("Cannot fetch for {}", getSite().getAllNewsHref());
			logger.error(e.toString());
			throw new BusinessException();
		}

		// call overridden method getElements for each site 
		Elements elements = getElements(document);

		for (Element element : elements) {
			
			// call overridden method getHref for each site
			String href = getHref(element);

			String html;

			// TODO parse url and filter it .. call fetcher method filterURI
			try {
				html = MyUtils.fetchContent(getSite().getHref() + href);
			} catch (Exception e) {
				logger.error("Cannot fetch : " + getSite().getHref() + href);
				continue;
			}

			Document doc = MyUtils.parseHTML(html);

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
			content.setContent(text)
					.setMd5Href(MyUtils.getMD5(getSite().getHref() + href))
					.setHref(getSite().getHref() + href)
					.setMd5Content(MyUtils.getMD5(text))
					.setTitle(title);
			// @formatter:on

			articles.put(MyUtils.getMD5(getSite().getHref() + href), content);
		}

		return articles;
	}

	private Document fetchDocument() throws IOException {

		CloseableHttpResponse httpResponse = null;
		try {
			httpResponse = (CloseableHttpResponse) httpClient
					.execute(new HttpGet(getSite().getAllNewsHref()));

			if (httpResponse.getStatusLine().getStatusCode() < 200
					|| httpResponse.getStatusLine().getStatusCode() >= 400) {
				throw new IOException("Got bad response, error code = "
						+ httpResponse.getStatusLine().getStatusCode());
			}

			HttpEntity entity = httpResponse.getEntity();

			String htmlContent = IOUtils.toString(entity.getContent(), "utf-8");

			Document document = Jsoup.parse(htmlContent);

			return document;

		} catch (IOException e) {
			logger.error(e.toString());
			throw e;
		} finally {
			httpResponse.close();
		}

	}

	/**
	 * @return the site
	 */
	public Site getSite() {
		return site;
	}

	/**
	 * @param site
	 *            the site to set
	 */
	public void setSite(Site site) {
		this.site = site;
	}
}
