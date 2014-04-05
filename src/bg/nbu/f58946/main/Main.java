package bg.nbu.f58946.main;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.jsoup.nodes.Document;

import com.twmacinta.util.MD5;

import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.Feeders;
import bg.nbu.f58946.parsers.FetchTextFactory;
import bg.nbu.f58946.parsers.IFetchText;
import bg.nbu.f58946.parsers.IParser;
import bg.nbu.f58946.parsers.ParserFactory;

public class Main {

	public static void main(String[] args) throws IOException,
			BusinessException {
		testMd5();
	}

	static void testMd5() throws UnsupportedEncodingException {		
		String url = "http://www.segabg.com/article.php?id=691926" ; 
		MD5 md5 = new MD5() ; 
		md5.Update(url,"UTF8");		
		String hash = md5.asHex() ; 
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
