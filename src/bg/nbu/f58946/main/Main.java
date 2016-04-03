package bg.nbu.f58946.main;

import java.io.IOException;
import java.util.ArrayList;

import org.elasticsearch.client.Client;
import org.elasticsearch.node.NodeBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.syndication.io.FeedException;

import bg.nbu.f58946.bo.ALL_SITES;
import bg.nbu.f58946.exceptions.BusinessException;
import bg.nbu.f58946.jobs.ArticleCollector;

public class Main {

	final static Logger logger = LoggerFactory.getLogger(Main.class);
	public static Client client;
	public static String INDEX = "news";
	/**
	 * Array with sites which will e collected
	 */
	public final static ArrayList<ALL_SITES> allowedSites = Main.allowedSites(); // ;

	public static void main(String[] args)
			throws IOException, BusinessException, IllegalArgumentException, FeedException {

		logger.debug(allowedSites.toString());

		startESClient();
		startArticleCollector();

	}

	private static void startESClient() {
		client = NodeBuilder.nodeBuilder().client(true).node().client();
	}

	private static ArrayList<ALL_SITES> allowedSites() {
		ArrayList<ALL_SITES> a = new ArrayList<ALL_SITES>();
		// a.add(ALL_SITES.DNEVNIK);
		a.add(ALL_SITES.TRUD);
		// a.add(ALL_SITES.DNEVNIK);
		// a.add(ALL_SITES.DNEVNIK);
		return a;
	}

	/**
	 * Start thread for collecting artciles
	 */
	private static void startArticleCollector() {
		new Thread(new ArticleCollector(), "TAC").start();
	}

}
