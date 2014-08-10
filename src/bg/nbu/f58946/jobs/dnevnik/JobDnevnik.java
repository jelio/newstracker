package bg.nbu.f58946.jobs.dnevnik;

import java.io.IOException;
import java.net.MalformedURLException;
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

import bg.nbu.f58946.bo.Content;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.main.Main;
import bg.nbu.f58946.parsers.Feeders;
import bg.nbu.f58946.parsers.FetchTextFactory;
import bg.nbu.f58946.parsers.IFetchText;
import bg.nbu.f58946.parsers.IParser;
import bg.nbu.f58946.parsers.ParserFactory;
import bg.nbu.f58946.utils.Utils;

public class JobDnevnik implements Runnable {

	String todayUrl = "http://www.dnevnik.bg/allnews/today/";
	String baseUrl = "http://www.dnevnik.bg";
	HttpClient httpClient;
	final static Logger logger = LoggerFactory.getLogger(JobDnevnik.class);

	public JobDnevnik(HttpClient httpClient) {
		this.httpClient = httpClient;
	}

	@Override
	public void run() {
		try {

			/*
			 * //Old implementation String rowHTML = IOUtils.toString((new
			 * URL(todayUrl)).openStream(), "utf-8"); Document document =
			 * Jsoup.parse(rowHTML); Elements matchedArticles =
			 * document.getElementsByClass("info");
			 */

			CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient
					.execute(new HttpGet(todayUrl));

			try {

				if (httpResponse.getStatusLine().getStatusCode() < 200
						|| httpResponse.getStatusLine().getStatusCode() >= 400) {
					throw new IOException("Got bad response, error code = "
							+ httpResponse.getStatusLine().getStatusCode());
				}

				HttpEntity entity = httpResponse.getEntity();

				String htmlContent = IOUtils.toString(entity.getContent(),
						"utf-8");

				Document document = Jsoup.parse(htmlContent);

				Elements elements = document.select(".info > a");

				int cnt = 0;
				for (Element element : elements) {

					String href = element.attr("href");

					IParser myParser = ParserFactory.getParser(Feeders.DNEVNIK,
							href);
					IFetchText fetcher = FetchTextFactory
							.fetchText(Feeders.DNEVNIK);

					String html ; 
					
					try {
						html = myParser.fetchContent(baseUrl + href);
					} catch (Exception e) {
						logger.error("Cannot fetch : " + baseUrl + href);
						continue;
					}

					Document doc = myParser.parseHTML(html);

					String text;
					String title;

					try {

						text = fetcher.getArticleText(doc);
						title = fetcher.getTitle(doc);

					} catch (BusinessException e) {
						System.err.println("Error at " + baseUrl + href);
						continue;
					}

					String md5Href = Utils.getMD5(baseUrl + href);
					String md5Content = Utils.getMD5(text);

					Content content = new Content();

					content.setContent(text).setMd5Href(md5Href)
							.setHref(baseUrl + href).setMd5Content(md5Content)
							.setTitle(title);

					Map<String, Content> tmp;

					if (Main.linkMap.containsKey(Feeders.DNEVNIK)) {
						tmp = Main.linkMap.get(Feeders.DNEVNIK);
					} else {
						tmp = new HashMap<String, Content>();
					}

					tmp.put(md5Href, content);

					Main.linkMap.put(Feeders.DNEVNIK, tmp);

					// System.out.println(content);

					if (cnt++ > 10)
						break;
				}

			} finally {
				httpResponse.close();
			}

			System.out.println(Main.linkMap);

			/*
			 * while (true) {
			 * 
			 * try { Thread.sleep(100); } catch (InterruptedException e) {
			 * e.printStackTrace(); } }
			 */

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			;
		}

	}
}
