package bg.nbu.f58946.main;

import java.io.IOException;

import org.jsoup.nodes.Document;

import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.parsers.Feeders;
import bg.nbu.f58946.parsers.FetchTextFactory;
import bg.nbu.f58946.parsers.IFetchText;
import bg.nbu.f58946.parsers.IParser;
import bg.nbu.f58946.parsers.ParserFactory;

public class Main {

	public static void main(String[] args) throws IOException, BusinessException {
		String url = "http://www.dnevnik.bg/bulgaria/2014/03/23/2266770_borisov_kolkoto_po-dobur_stavam_i_iskam_mir_da_ima/";

		IParser myParser = ParserFactory.getParser(Feeders.DNEVNIK, url);
		IFetchText fetcher = FetchTextFactory.fetchText(Feeders.DNEVNIK);

		String html = myParser.fetchContent(url);
		Document doc = myParser.parseHTML(html);

		String text = fetcher.getArticleText(doc);

		System.out.println(text);
	}

}
