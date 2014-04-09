package bg.nbu.f58946.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import bg.nbu.f58946.database.MyDataSource;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.Feeders;
import bg.nbu.f58946.parsers.FetchTextFactory;
import bg.nbu.f58946.parsers.IFetchText;
import bg.nbu.f58946.parsers.IParser;
import bg.nbu.f58946.parsers.ParserFactory;

import com.sun.syndication.io.FeedException;
import com.twmacinta.util.MD5;

public class Main {

	public static void main(String[] args) throws IOException,
			BusinessException, IllegalArgumentException, FeedException {
		testMysql();
	}

	static void testMysql() {
		String query = "SELECT table_name FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'nbu'";

		try {
				
		
			Connection connection = MyDataSource.getInstance("").getConnection() ; 
			
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			while (resultSet.next()) {
				String tableName = resultSet.getString(1);
				System.out.println("Table name : " + tableName);
			}
			connection.close();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (BusinessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	static void getAllDir() throws MalformedURLException, IOException,
			IllegalArgumentException, FeedException {
		String sUrl = "http://dnes.dir.bg/";
		String baseUrl = "http://dnes.dir.bg/";

		HttpClient httpClient = HttpClients
				.custom()
				.setUserAgent(
						"Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36")
				.build();

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
		String baseUrl = "http://www.segabg.com/";

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

	static void getAllNewsDnevnik() throws MalformedURLException, IOException {
		String url = "http://www.dnevnik.bg/allnews/today/";
		String baseUrl = "http://www.dnevnik.bg";

		String rowHTML = IOUtils.toString((new URL(url)).openStream(), "utf-8");
		Document document = Jsoup.parse(rowHTML);
		Elements matchedArticles = document.getElementsByClass("info");

		for (Element element : matchedArticles) {
			// System.out.println(element);

			Elements anchors = element.getElementsByTag("a");
			if (anchors.size() > 0) {
				Element myLink = anchors.get(0);
				String href = myLink.attr("href");
				System.out.println(baseUrl + href);
			}
			// System.out.println(anchors);
			// System.out.println("----------------");
			// break ;
		}
		// System.out.println(matchedArticles);

	}

	static void testMd5() throws UnsupportedEncodingException {
		String url = "http://www.segabg.com/article.php?id=691926";
		MD5 md5 = new MD5();
		md5.Update(url, "UTF8");
		String hash = md5.asHex();
		System.out.println(hash.toUpperCase());
	}

	static void testSega() throws IOException, BusinessException {
		String url = "http://www.segabg.com/article.php?id=691926";

		IParser myParser = ParserFactory.getParser(Feeders.SEGA, url);
		IFetchText fetcher = FetchTextFactory.fetchText(Feeders.SEGA);

		String html = myParser.fetchContent(url);
		Document doc = myParser.parseHTML(html);

		String text = fetcher.getArticleText(doc);

		System.out.println(text);

	}

	static void testOffnews() throws IOException, BusinessException {
		String url = "http://offnews.bg/news/%D0%A1%D0%B2%D1%8F%D1%82-_12/TNS-Opinion-%D0%94%D0%B5%D1%81%D0%BD%D0%B8%D1%86%D0%B0%D1%82%D0%B0-%D0%B8%D0%B7%D0%BF%D1%80%D0%B5%D0%B2%D0%B0%D1%80%D0%B2%D0%B0-%D0%BB%D0%B5%D0%B2%D0%B8%D1%86%D0%B0%D1%82%D0%B0-%D0%B2-%D0%B1%D0%B8%D1%82%D0%BA%D0%B0%D1%82%D0%B0-%D0%B7%D0%B0-%D0%95%D0%B2%D1%80%D0%BE%D0%BF%D0%B0%D1%80%D0%BB%D0%B0%D0%BC%D0%B5%D0%BD%D1%82%D0%B0_315420.html";

		IParser myParser = ParserFactory.getParser(Feeders.OFFNEWS, url);
		IFetchText fetcher = FetchTextFactory.fetchText(Feeders.OFFNEWS);

		String html = myParser.fetchContent(url);
		Document doc = myParser.parseHTML(html);

		String text = fetcher.getArticleText(doc);

		System.out.println(text);

	}

	static void testTrud() throws IOException, BusinessException {
		String url = "http://www.trud.bg/Article.asp?ArticleId=3819039";

		IParser myParser = ParserFactory.getParser(Feeders.TRUD, url);
		IFetchText fetcher = FetchTextFactory.fetchText(Feeders.TRUD);

		String html = myParser.fetchContent(url);
		Document doc = myParser.parseHTML(html);

		String text = fetcher.getArticleText(doc);

		System.out.println(text);

	}

	static void testBnt() throws IOException, BusinessException {
		String url = "http://bnt.bg/news/ekologiya-i-ustoychivo-razvitie/pcheli-izmirat-v-dobrichko";

		IParser myParser = ParserFactory.getParser(Feeders.DIRBG, url);
		IFetchText fetcher = FetchTextFactory.fetchText(Feeders.DIRBG);

		String html = myParser.fetchContent(url);
		Document doc = myParser.parseHTML(html);

		String text = fetcher.getArticleText(doc);

		System.out.println(text);

	}

	static void testDnevnik() throws IOException, BusinessException {
		String url = "http://www.dnevnik.bg/bulgaria/2014/03/23/2266770_borisov_kolkoto_po-dobur_stavam_i_iskam_mir_da_ima/";

		IParser myParser = ParserFactory.getParser(Feeders.DNEVNIK, url);
		IFetchText fetcher = FetchTextFactory.fetchText(Feeders.DNEVNIK);

		String html = myParser.fetchContent(url);
		Document doc = myParser.parseHTML(html);

		String text = fetcher.getArticleText(doc);

		System.out.println(text);

	}

}
