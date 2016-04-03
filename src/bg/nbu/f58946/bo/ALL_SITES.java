package bg.nbu.f58946.bo;

public enum ALL_SITES {
	DNEVNIK(1,"http://www.dnevnik.bg","http://www.dnevnik.bg/allnews/today"), 
	TRUD(2,"http://www.trud.bg","http://www.trud.bg/AllNews.asp"), 
	OFFNEWS(3,"http://offnews.bg","http://offnews.bg/news"), 
	SEGA(4,"http://sega.bg","http://sega.bg/news");

	private int code;
	private String href;
	private String allArticlesHref;

	ALL_SITES(int code, String href, String allArtcilesHref) {
		this.code = code;
		this.href = href;
		this.allArticlesHref = allArtcilesHref;
	}

	public int getCode() {
		return code;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getAllArticlesHref() {
		return allArticlesHref;
	}

	public void setAllArticlesHref(String allArticlesHref) {
		this.allArticlesHref = allArticlesHref;
	}
}
