package bg.nbu.f58946.main;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Iterator;
import java.util.List;

import javax.swing.text.html.HTML;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndEntryImpl;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.io.FeedException;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.twmacinta.util.MD5;

import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.Feeders;
import bg.nbu.f58946.parsers.FetchTextFactory;
import bg.nbu.f58946.parsers.IFetchText;
import bg.nbu.f58946.parsers.IParser;
import bg.nbu.f58946.parsers.ParserFactory;

public class Main {

	public static void main(String[] args) throws IOException,
			BusinessException, IllegalArgumentException, FeedException {
		getAllDir();
	}

	static void getAllDir() throws MalformedURLException, IOException, IllegalArgumentException, FeedException {
		String sUrl = "http://dnes.dir.bg/support/cat_rss.php?section=all";
		String baseUrl = "http://dnes.dir.bg/";

		URL url = new URL(sUrl) ; 
		
		SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(url.openStream()));
		
        System.out.println(feed);
        
        @SuppressWarnings("unchecked")
		List<SyndEntryImpl> entries = feed.getEntries();
        Iterator<SyndEntryImpl> itEntries = entries.iterator();
 
        while (itEntries.hasNext()) {
            SyndEntry entry = itEntries.next();
            System.out.println("Title: " + entry.getTitle());
            System.out.println("Link: " + entry.getLink());
            System.out.println("Author: " + entry.getAuthor());
            System.out.println("Publish Date: " + entry.getPublishedDate());
            System.out.println("Description: " + entry.getDescription().getValue());
            System.out.println();
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
			// System.out.println(anchors);
			// System.out.println("----------------");
			// break ;
		}
		// System.out.println(matchedArticles);

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

		IParser myParser = ParserFactory.getParser(Feeders.BNTBG, url);
		IFetchText fetcher = FetchTextFactory.fetchText(Feeders.BNTBG);

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
