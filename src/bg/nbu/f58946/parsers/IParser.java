package bg.nbu.f58946.parsers;

import java.io.IOException;

import org.jsoup.nodes.Document;

public interface IParser {
	String fetchContent(String url) throws IOException ; 
	Document parseHTML(String html)  ;
}
