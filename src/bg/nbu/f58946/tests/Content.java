package bg.nbu.f58946.tests;

import java.util.ArrayList;

public class Content {
	private String md5Href;
	private String href;
	private String content;
	private String md5Content;
	private ArrayList<String> words;
	private ArrayList<String> titleWords;
	private String title;
	
	
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
	public Content setMd5Href(String md5Href) {
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
	public Content setHref(String href) {
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
	public Content setContent(String content) {
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
	public Content setMd5Content(String md5Content) {
		this.md5Content = md5Content;
		return this;
	}

	/**
	 * @return the words
	 */
	public ArrayList<String> getWords() {
		return words;
	}

	/**
	 * @param words
	 *            the words to set
	 */
	public Content setWords(ArrayList<String> words) {
		this.words = words;
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
	public Content setTitle(String title) {
		this.title = title;
		return this;
	}
	
	/**
	 * @return the titleWords
	 */
	public ArrayList<String> getTitleWords() {
		return titleWords;
	}

	/**
	 * @param titleWords the titleWords to set
	 */
	public Content setTitleWords(ArrayList<String> titleWords) {
		this.titleWords = titleWords;
		return this ;
	}
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder() ; 
		b.append("\ntitle : ")
			.append(title)
			.append("\nhref : ")
			.append(href) 
			.append("\nmd5 href : ")
			.append(md5Href)
			.append("\nmd5Content : ")
			.append(md5Content)
			.append("\nContent : ")
			.append(content)			
			.append("\nwords " )
			.append(words.toString())
			.append("\ntitleWords " )
			.append(titleWords.toString()); 
		return b.toString();
	}


}
