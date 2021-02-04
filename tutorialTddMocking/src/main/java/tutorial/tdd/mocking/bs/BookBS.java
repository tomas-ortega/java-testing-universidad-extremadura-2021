package tutorial.tdd.mocking.bs;

import java.sql.Connection;

import tutorial.tdd.mocking.dao.BookDAO;
import tutorial.tdd.mocking.dao.FactoryBookDAO;
import tutorial.tdd.mocking.dao.InterfacesDAO;
import tutorial.tdd.mocking.domain.BookDTO;

public class BookBS implements InterfaceBS {
	private InterfacesDAO libroDao = FactoryBookDAO.getBookDAO();
	private Connection connectionReference;
	private DbConnection dbConnection;

	public void setDAO(BookDAO bookDAO) {
		this.libroDao = bookDAO;
	}
	
	public void setConnection(DbConnection dbConnection) throws Exception {
		this.dbConnection = dbConnection;
		this.connectionReference = dbConnection.getDbConnection();
	}

	public void insert(BookDTO book) throws Exception {
		try {
			if (this.connectionReference == null) {
				this.connectionReference = new DbConnection().getDbConnection();
			}
			
			libroDao.insert(book, connectionReference);
		} catch (Exception ex) {
			System.out.println(ex);
			throw ex;
		}
	}

	@Override
	public BookDTO searchBookById(Integer bookId) throws Exception {	
		try {
			if (this.connectionReference == null) {
				connectionReference = new DbConnection().getDbConnection();
			}
			
			return libroDao.searchBookById(bookId, connectionReference);
		} catch (Exception ex) {
			System.out.println(ex);
			throw ex;
		} finally {
			this.dbConnection.closeConnection(this.connectionReference);
		}
	}
	
	public String testVerifyInvocationOrder() {
		return "Hola";
	}
}
