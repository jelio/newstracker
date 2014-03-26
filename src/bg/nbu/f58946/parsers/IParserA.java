package bg.nbu.f58946.parsers;

import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public abstract class IParserA implements IParser {
	public String CHARSET = "utf-8";

	@Override
	public String fetchContent(String url) throws IOException {
		return IOUtils.toString((new URL(url)).openStream(), this.CHARSET);
	}

	@Override
	public Document parseHTML(String html) {
		return Jsoup.parse(html);
	}

}
