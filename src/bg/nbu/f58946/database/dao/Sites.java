package bg.nbu.f58946.database.dao;

public class Sites {
	private int id;
	private String name;
	private String href;
	private String allArticlesHref;
	private int frozen;


	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public int getFrozen() {
		return frozen;
	}

	public void setFrozen(int frozen) {
		this.frozen = frozen;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Sites [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(name);
		builder.append(", href=");
		builder.append(href);
		builder.append(", allArticlesHref=");
		builder.append(allArticlesHref);
		builder.append(", frozen=");
		builder.append(frozen);
		builder.append("]");
		return builder.toString();
	}
}
