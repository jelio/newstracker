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

		try {

			Document document = fetchDocument();

			Elements elements = getElements(document);

			for (Element element : elements) {

				String href = getHref(element);

				String html;

				// TODO parse url and filter it ..
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

					text = getArticleText(doc);
					title = getTitle(doc);

				} catch (BusinessException e) {
					System.err
							.println("Error at " + getSite().getHref() + href);
					continue;
				}

				String md5Href = MyUtils.getMD5(getSite().getHref() + href);
				String md5Content = MyUtils.getMD5(text);

				Article content = new Article();

				content.setContent(text).setMd5Href(md5Href)
						.setHref(getSite().getHref() + href)
						.setMd5Content(md5Content).setTitle(title);

				articles.put(md5Href, content);

				return articles;
			}

		} catch (IOException e) {
			logger.error(e.toString());
			throw new BusinessException();
		}

		return null;
	}

	private Document fetchDocument() throws IOException {

		HttpEntity entity = getHttpEntity();

		String htmlContent = IOUtils.toString(entity.getContent(), "utf-8");

		Document document = Jsoup.parse(htmlContent);

		return document;

	}

	/**
	 * Fetch html content and convert it to HttpEntity
	 * @return HttpEntity
	 * @throws IOException
	 */
	private HttpEntity getHttpEntity() throws IOException {
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

			return entity;
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
