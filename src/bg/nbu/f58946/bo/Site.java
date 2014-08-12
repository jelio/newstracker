package bg.nbu.f58946.bo;

public class Site {
	private String name; 
	private int id ; 
	private String href ; 
	private String allNewsHref ;
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
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @return the href
	 */
	public String getHref() {
		return href;
	}
	/**
	 * @param href the href to set
	 */
	public void setHref(String href) {
		this.href = href;
	}
	/**
	 * @return the allNewsHref
	 */
	public String getAllNewsHref() {
		return allNewsHref;
	}
	/**
	 * @param allNewsHref the allNewsHref to set
	 */
	public void setAllNewsHref(String allNewsHref) {
		this.allNewsHref = allNewsHref;
	} 
	
	@Override
	public String toString() {
		StringBuilder b = new StringBuilder() ; 
		b.append("\nId : ")
			.append(id)
			.append("\nname : ")
			.append(name)
			.append("\nhref : ")
			.append(href) 
			.append("\nAllNewsHref : ")
			.append(allNewsHref); 
		return b.toString();
	}
	
}
