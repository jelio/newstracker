package bg.nbu.f58946.bo;


public class Article {
	private String md5Href;
	private String href;
	private String content;
	private String md5Content;
	private String title;
	private String author ; 
	private int siteId ; 
	private int id; 
	
	/**
	 * @return the md5Href
	 */
	public String getMd5Href() {
		return md5Href;
	}

	/**
	 * @param md5Href
	 *            the md5Href to set
	 */
	public Article setMd5Href(String md5Href) {
		this.md5Href = md5Href;
		return this;
	}

	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}

	/**
	 * @param href
	 *            the href to set
	 */
	public Article setHref(String href) {
		this.href = href;
		return this;
	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return content;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	public Article setContent(String content) {
		this.content = content;
		return this;
	}

	/**
	 * @return the md5Content
	 */
	public String getMd5Content() {
		return md5Content;
	}

	/**
	 * @param md5Content
	 *            the md5Content to set
	 */
	public Article setMd5Content(String md5Content) {
		this.md5Content = md5Content;
		return this;
	}


	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public Article setTitle(String title) {
		this.title = title;
		return this;
	}

	/**
	 * @return the siteId
	 */
	public int getSiteId() {
		return siteId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(int siteId) {
		this.siteId = siteId;
	}

	

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder() ; 
		b.append("\nId : ")
			.append(id)
			.append("\ttitle : ")
			.append(title)
			.append("\thref : ")
			.append(href) 
			.append("\tsite id : ")
			.append(siteId)	
			.append("\tmd5 href : ")
			.append(md5Href)
			.append("\tmd5Content : ")
			.append(md5Content)
			.append("\tContent : ")
			.append(content)
			.append("\tAuthor : ")
			.append(author); 
		return b.toString();
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	

}
