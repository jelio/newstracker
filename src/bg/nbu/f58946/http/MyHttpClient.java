package bg.nbu.f58946.http;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClients;

public class MyHttpClient {
	
	public MyHttpClient() {
		
	}
	
	/**
	 * @todo - parameterize this !
	 * @return
	 */
	public HttpClient getHttpClient(){
		
		HttpClient httpClient = HttpClients
				.custom()
				.setUserAgent(
						"Mozilla/5.0 (Windows NT 6.2; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/32.0.1667.0 Safari/537.36")
				.build();
		return httpClient ; 
	}
}
