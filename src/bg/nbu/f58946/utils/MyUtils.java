package bg.nbu.f58946.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.twmacinta.util.MD5;

import bg.nbu.f58946.bo.Article;
import bg.nbu.f58946.http.MyHttpClient;

public class MyUtils {

	private static final String CHARSET = "utf-8";

	public static String getMD5(String content) {

		MD5 md5 = new MD5();
		try {
			md5.Update(content, "UTF8");
		} catch (UnsupportedEncodingException e) {
			md5.Update(content);
		}

		return md5.asHex().toUpperCase();

	}

	public static ArrayList<String> toWords(String content) {
		Scanner s = new Scanner(content);
		s.useDelimiter("\\s");

		ArrayList<String> wordsArray = new ArrayList<>();

		while (s.hasNext()) {
			wordsArray.add(s.next().toLowerCase());
		}

		s.close();

		return wordsArray;
	}

	public static Document fetchDocument(String url) throws IOException {

		CloseableHttpResponse httpResponse = null;
		HttpClient httpClient = (new MyHttpClient()).getHttpClient();
		try {
			httpResponse = (CloseableHttpResponse) httpClient.execute(new HttpGet(url));

			if (httpResponse.getStatusLine().getStatusCode() < 200
					|| httpResponse.getStatusLine().getStatusCode() >= 400) {
				throw new IOException("Got bad response, error code = " + httpResponse.getStatusLine().getStatusCode());
			}

			HttpEntity entity = httpResponse.getEntity();

			String htmlContent = IOUtils.toString(entity.getContent(), "utf-8");

			Document document = Jsoup.parse(htmlContent);

			return document;

		} catch (IOException e) {
			throw e;
		} finally {
			httpResponse.close();
		}

	}

	@Deprecated
	public static String fetchContent(String url) throws IOException {
		return IOUtils.toString((new URL(url)).openStream(), CHARSET);
	}

	@Deprecated
	public static Document parseHTML(String html) {
		return Jsoup.parse(html);
	}

	public static Map<String, Object> articleToMap(Article a) {
		Map<String, Object> m = new HashMap<String, Object>();
		m.put("author", a.getAuthor());
		m.put("content", a.getContent());
		m.put("md5_content", a.getMd5Content());
		m.put("href", a.getHref());
		return m;
	}
}
