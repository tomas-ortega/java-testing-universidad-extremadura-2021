package tutorial.tdd.mocking.bs;


import tutorial.tdd.mocking.domain.BookDTO;

public interface InterfaceBS {
	public void insert(BookDTO newBook) throws Exception;
	public BookDTO searchBookById(Integer bookId) throws Exception;
}
