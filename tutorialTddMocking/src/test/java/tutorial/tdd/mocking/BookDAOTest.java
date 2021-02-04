package tutorial.tdd.mocking;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.dbunit.Assertion;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.xml.sax.InputSource;

import tutorial.tdd.mocking.bs.BookBS;
import tutorial.tdd.mocking.domain.BookDTO;

public class BookDAOTest {
	private static IDataSet initialDataSet;
	private static IDatabaseConnection databaseConnection;
	
	private BookBS bookBs = new BookBS();
	
	@BeforeAll
	static void getConnection() {
		BookDAOTest.databaseConnection = BookDAOTestHelper.getConnection();
		BookDAOTest.initialDataSet = BookDAOTestHelper.getInitialDataSet();
	}
	
	@BeforeEach
	void getSetUpOperation() {
		BookDAOTestHelper.importData(BookDAOTest.databaseConnection);
	}
	
	@Test
	@DisplayName("Test check rowCount initial DataSet")
	public void testDatosCargados() throws Exception {
		assertNotNull(BookDAOTest.initialDataSet);
		
		int rowCount = BookDAOTest.initialDataSet.getTable("book").getRowCount();
		
		assertEquals(2, rowCount);
	}
	
	@Test
	@DisplayName("Test read all rows from book table")
	public void testReadAllRows() throws Exception {
		QueryDataSet queryDataSet = new QueryDataSet(BookDAOTest.databaseConnection);
		
		queryDataSet.addTable("book", "SELECT * FROM book");
		Assertion.assertEquals(BookDAOTest.initialDataSet, queryDataSet);
	}
	
	@Test
	@DisplayName("Test insert book with id 3")
	public void testInsertBookWithId3() throws Exception {
		IDataSet expectedDataSet = new FlatXmlDataSet(
				new FlatXmlProducer(new InputSource(BookDAOTestHelper.testInsertBookWithId3)));
		
		BookDTO libro = new BookDTO(3, "La historia interminable", "Michael Ende", "Alfaguara", 496);
		bookBs.insert(libro);
		
		String[] table = { "book" };
		IDataSet realDataSet = BookDAOTest.databaseConnection.createDataSet(table);
		
		Assertion.assertEquals(expectedDataSet, realDataSet);
	}
	
	@AfterEach
	void getTearDownOperation() {
		BookDAOTestHelper.deleteData(BookDAOTest.databaseConnection);
	}
}
