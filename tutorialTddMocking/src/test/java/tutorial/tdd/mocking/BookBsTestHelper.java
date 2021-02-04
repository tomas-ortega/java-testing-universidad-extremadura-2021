package tutorial.tdd.mocking;

import tutorial.tdd.mocking.domain.BookDTO;

public class BookBsTestHelper {
	private BookDTO bookNotFound;
	private BookDTO bookWithId1;
	
	public BookBsTestHelper() {
		this.initBookNotFound();
		this.initBookWithId1();
	}
	
	private void initBookNotFound() {
		this.bookNotFound = null;
	}
	
	private void initBookWithId1() {
		this.bookWithId1 = new BookDTO();
		
		this.bookWithId1.setId(1);
		this.bookWithId1.setTitle("Los pilares de la tierra");
		this.bookWithId1.setAuthor("Ken Follet");
		this.bookWithId1.setPages(498);
	}
	
	public BookDTO getBookNotFound() {
		return this.bookNotFound;
	}
	
	public BookDTO getBookWithId1() {
		return this.bookWithId1;
	}
}
