package bg.nbu.f58946.main;

import org.elasticsearch.node.NodeBuilder;

import bg.nbu.f58946.bo.Article;
import bg.nbu.f58946.utils.MyUtils;
import bg.nbu.f58946.utils.TempUtil;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.Client;

public class TestES {
	private static Client client;
	public static final String INDEX = "news";
	public static final String TYPE = "my_type";

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		client = NodeBuilder.nodeBuilder().client(true).node().client();
		// testQuery();
		testArticle();
	}

	private static void testArticle() throws InterruptedException {
		Article a = TempUtil.createTestArticle();
		Map<String, Object> m = MyUtils.articleToMap(a);
		m.put("insert_date", new Date()) ; 
		IndexResponse response = client.prepareIndex(INDEX, a.getSiteId())
						.setId(a.getMd5Href())
						.setSource(m)
						.get();
		System.out.println("Query send");
		System.out.println(response.toString());
	}

	@SuppressWarnings("unused")
	private static void testQuery() throws InterruptedException, ExecutionException {
		SearchResponse response = client.prepareSearch("my_index").setTypes("my_type")
				.setSearchType(SearchType.DFS_QUERY_THEN_FETCH).execute().get();
		System.out.println(response.toString());

	}

}
