package tutorial.tdd.mocking;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.inOrder;

import java.sql.Connection;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import tutorial.tdd.mocking.bs.BookBS;
import tutorial.tdd.mocking.bs.DbConnection;
import tutorial.tdd.mocking.dao.BookDAO;
import tutorial.tdd.mocking.domain.BookDTO;

@ExtendWith(MockitoExtension.class)
public class BookBsTest {
	
	private InOrder callOrder;
	
	@Mock
	private Connection connection;
	
	@Mock
	private DbConnection dbConnection;
	
	@Mock
	private BookDAO bookDAO;
	
	@InjectMocks
	@Spy
	private BookBS bookBS;
	
	private static BookBsTestHelper BSHelper;
	
	@BeforeAll
	static void setUpClass() {
		BSHelper = new BookBsTestHelper();
	}
	
	@BeforeEach
	void setUpMocks() throws Exception {
		this.bookBS.setDAO(this.bookDAO);
		
		this.initializeMockDbConnection();
		this.initializeMockBookDAO();
		
		
		this.bookBS.setConnection(this.dbConnection);
		
		this.callOrder = inOrder(this.bookBS);
	}
	
	@Test
	void searchBookWithId1() throws Exception {
		BookDTO foundBook = null;
		
		foundBook = this.bookBS.searchBookById(BSHelper.getBookWithId1().getId());
		this.bookBS.testVerifyInvocationOrder();
		
		assertTrue(foundBook.getId().equals(BSHelper.getBookWithId1().getId()));
		
		this.callOrder.verify(this.bookBS).searchBookById(BSHelper.getBookWithId1().getId());
		this.callOrder.verify(this.bookBS).testVerifyInvocationOrder();
	}
	
	private void initializeMockDbConnection() throws Exception {
		when(this.dbConnection.getDbConnection())
			.thenReturn(this.connection);
	}
	
	private void initializeMockBookDAO() throws Exception {
		when(this.bookDAO.searchBookById(BSHelper.getBookWithId1().getId(), 
										this.connection))
			.thenReturn(BSHelper.getBookWithId1());
	}
}
