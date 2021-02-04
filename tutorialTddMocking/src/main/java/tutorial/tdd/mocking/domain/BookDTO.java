package tutorial.tdd.mocking.domain;

public class BookDTO {
	private Integer id;
	private String title;
	private String author;
	private String editorial;
	private Integer pages;
	
	
	
	public BookDTO(Integer id, String title, String author, String editorial, Integer pages) {
		super();
		this.id = id;
		this.title = title;
		this.author = author;
		this.editorial = editorial;
		this.pages = pages;
	}
	
	public BookDTO() {
		
	}
	
	public BookDTO(Integer id) {
		this.id = id;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getEditorial() {
		return editorial;
	}
	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	public Integer getPages() {
		return pages;
	}
	public void setPages(Integer pages) {
		this.pages = pages;
	}
}
