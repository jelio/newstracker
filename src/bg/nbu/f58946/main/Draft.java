package bg.nbu.f58946.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.syndication.io.FeedException;

import bg.nbu.f58946.bo.ALL_SITES;
import bg.nbu.f58946.bo.Site;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.http.MyHttpClient;
import bg.nbu.f58946.parsers.FetchTextFactory;
import bg.nbu.f58946.parsers.IFetchText;
import bg.nbu.f58946.utils.MyUtils;

public class Draft {
	static void getAllDir() throws MalformedURLException, IOException,
			IllegalArgumentException, FeedException {
		String sUrl = "http://dnes.dir.bg/";
		String baseUrl = "http://dnes.dir.bg/";

		HttpClient httpClient = (new MyHttpClient()).getHttpClient();

		CloseableHttpResponse httpResponse = (CloseableHttpResponse) httpClient
				.execute(new HttpGet(sUrl));

		try {

			if (httpResponse.getStatusLine().getStatusCode() < 200
					|| httpResponse.getStatusLine().getStatusCode() >= 400) {
				throw new IOException("Got bad response, error code = "
						+ httpResponse.getStatusLine().getStatusCode());
			}

			HttpEntity entity = httpResponse.getEntity();

			String htmlContent = IOUtils.toString(entity.getContent(), "utf-8");

			Document document = Jsoup.parse(htmlContent);

			Elements elements = document.select("#headlinesonly > a");

			for (Element element : elements) {
				String href = element.attr("href");
				System.out.println(baseUrl + href);
			}

		} finally {
			httpResponse.close();
		}
	}

	static void getAllTrud() throws MalformedURLException, IOException {
		String url = "http://www.trud.bg/Rubric.asp?RubricId=439";
		String baseUrl = "http://www.trud.bg";

		String rowHTML = IOUtils.toString((new URL(url)).openStream(), "utf-8");
		Document document = Jsoup.parse(rowHTML);
		String mainNewsHref = document.select(".main-new-article > h1 > a")
				.attr("href");
		System.out.println(baseUrl + mainNewsHref);

		Elements elements = document.select(".news > a");

		for (Element element : elements) {
			String href = element.attr("href");
			System.out.println(baseUrl + href);
		}

	}

	static void getAllOffnews() throws MalformedURLException, IOException {
		String url = "http://offnews.bg/news/%D0%9D%D0%BE%D0%B2%D0%B8%D0%BD%D0%B8_2/";
		String baseUrl = "http://offnews.bg";

		String rowHTML = IOUtils.toString((new URL(url)).openStream(), "utf-8");
		Document document = Jsoup.parse(rowHTML);
		Elements matchedArticles = document
				.getElementsByClass("cat_list_s_title");

		for (Element element : matchedArticles) {
			Elements anchors = element.getElementsByTag("a");
			if (anchors.size() > 0) {
				Element myLink = anchors.get(0);
				String href = myLink.attr("href");
				System.out.println(baseUrl + href);
			}
		}

	}

	static void getAllSega() throws MalformedURLException, IOException {
		String url = "http://www.segabg.com/index.php?sid=2";
		// String baseUrl = "http://www.segabg.com/";

		String rowHTML = IOUtils.toString((new URL(url)).openStream(), "utf-8");
		Document document = Jsoup.parse(rowHTML);
		Elements matchedArticles = document.getElementsByClass("a_title");

		for (Element element : matchedArticles) {
			// System.out.println(element);

			Elements anchors = element.getElementsByTag("a");
			if (anchors.size() > 0) {
				Element myLink = anchors.get(0);
				String href = myLink.attr("href");
				System.out.println(href);
			}
		}

	}

	static void testAll() throws IOException, BusinessException {
		String url = "http://bnt.bg/news/ekologiya-i-ustoychivo-razvitie/pcheli-izmirat-v-dobrichko";
		url = "http://www.trud.bg/Article.asp?ArticleId=3819039";
		url = "http://offnews.bg/news/%D0%A1%D0%B2%D1%8F%D1%82-_12/TNS-Opinion-%D0%94%D0%B5%D1%81%D0%BD%D0%B8%D1%86%D0%B0%D1%82%D0%B0-%D0%B8%D0%B7%D0%BF%D1%80%D0%B5%D0%B2%D0%B0%D1%80%D0%B2%D0%B0-%D0%BB%D0%B5%D0%B2%D0%B8%D1%86%D0%B0%D1%82%D0%B0-%D0%B2-%D0%B1%D0%B8%D1%82%D0%BA%D0%B0%D1%82%D0%B0-%D0%B7%D0%B0-%D0%95%D0%B2%D1%80%D0%BE%D0%BF%D0%B0%D1%80%D0%BB%D0%B0%D0%BC%D0%B5%D0%BD%D1%82%D0%B0_315420.html";
		url = "http://www.segabg.com/article.php?id=691926";

		IFetchText fetcher = FetchTextFactory.getFetcher(ALL_SITES.DNEVNIK);

		String html = MyUtils.fetchContent(url);
		Document doc = MyUtils.parseHTML(html);

		String text = fetcher.getArticleText(doc);

		System.out.println(text);

	}
}
