package tutorial.tdd.mocking.dao;

import java.sql.Connection;
import java.sql.SQLException;

import tutorial.tdd.mocking.domain.BookDTO;

public interface InterfacesDAO {
	public void insert(BookDTO book, Connection connectionReference) throws	SQLException;
	public BookDTO searchBookById(Integer bookId, Connection connectionReference) throws SQLException;
}
