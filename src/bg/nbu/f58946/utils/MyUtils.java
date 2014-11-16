package bg.nbu.f58946.utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.apache.commons.io.IOUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.twmacinta.util.MD5;

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

	public static String fetchContent(String url) throws IOException {
		return IOUtils.toString((new URL(url)).openStream(), CHARSET);
	}

	public static Document parseHTML(String html) {
		return Jsoup.parse(html);
	}
}
