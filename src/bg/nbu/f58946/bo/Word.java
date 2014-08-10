package bg.nbu.f58946.bo;

public class Word {
	private String word;
	private int id;
	private int parentId;
	private int isName;
	private int isSmallWord;
	private int langId;

	/**
	 * @return the word
	 */
	public String getWord() {
		return word;
	}

	/**
	 * @param word
	 *            the word to set
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the parentId
	 */
	public int getParentId() {
		return parentId;
	}

	/**
	 * @param parentId
	 *            the parentId to set
	 */
	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the isName
	 */
	public int getIsName() {
		return isName;
	}

	/**
	 * @param isName
	 *            the isName to set
	 */
	public void setIsName(int isName) {
		this.isName = isName;
	}

	/**
	 * @return the isSmallWord
	 */
	public int getIsSmallWord() {
		return isSmallWord;
	}

	/**
	 * @param isSmallWord
	 *            the isSmallWord to set
	 */
	public void setIsSmallWord(int isSmallWord) {
		this.isSmallWord = isSmallWord;
	}

	/**
	 * @return the langId
	 */
	public int getLangId() {
		return langId;
	}

	/**
	 * @param langId
	 *            the langId to set
	 */
	public void setLangId(int langId) {
		this.langId = langId;
	}

}
