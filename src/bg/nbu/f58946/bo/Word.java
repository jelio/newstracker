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
	 * @return
	 */
	public Word setWord(String word) {
		this.word = word;
		return this;
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
	public Word setId(int id) {
		this.id = id;
		return this;
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
	public Word setParentId(int parentId) {
		this.parentId = parentId;
		return this;
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
	public Word setIsName(int isName) {
		this.isName = isName;
		return this;
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
	public Word setIsSmallWord(int isSmallWord) {
		this.isSmallWord = isSmallWord;
		return this;
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
	public Word setLangId(int langId) {
		this.langId = langId;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder b = new StringBuilder();
		b.append("\nId : ").append(id).append("\nWord : ").append(word)
				.append(isName).append("\nisSmallWord : ").append(isSmallWord)
				.append("\nlandId : ").append(langId);

		return b.toString();
	}

}
