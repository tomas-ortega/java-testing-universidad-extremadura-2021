package tutorial.tdd.mocking;

import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;

import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.dataset.xml.FlatXmlProducer;
import org.dbunit.operation.DatabaseOperation;
import org.xml.sax.InputSource;

public class BookDAOTestHelper {
	private static String databaseUser = "root";
	private static String databasePassword = "mysql";
	private static String databaseName = "test_tdd";
	private static String driverName = "com.mysql.jdbc.Driver";
	private static String databaseURL = "jdbc:mysql://localhost/" + databaseName;
	
	private static String inputXml = "db/inputData.xml";
	public static String testDeleteBookWithId1 = "db/testDeleteBookWithId1.xml";
	public static String testEditBookWithId2 = "db/testEditBookWithId2.xml";
	public static String testInsertBookWithId3 = "db/testInsertBookWithId3.xml";
	
	public static IDatabaseConnection getConnection() {
		Connection jdbcConnection = null;
		IDatabaseConnection databaseConnection = null;
		try {
			Class.forName(driverName);
			jdbcConnection = DriverManager.getConnection(databaseURL, databaseUser, databasePassword);
			databaseConnection = new DatabaseConnection(jdbcConnection);
			return databaseConnection;
		} catch (Exception error) {
			System.out.println(error);
			return databaseConnection;
		}
	}
	
	public static FlatXmlDataSet getInitialDataSet() {
		FlatXmlDataSet xmlDataSet = null;
		try {
			xmlDataSet = new FlatXmlDataSet(new FlatXmlProducer(new InputSource(inputXml)));
			return xmlDataSet;
		} catch (Exception error) {
			System.out.println(error);
			return xmlDataSet;
		}
	}
	
	public static void deleteData(IDatabaseConnection databaseConnection) {
		try {
			FlatXmlDataSetBuilder datasetBuilder = new FlatXmlDataSetBuilder();
			FlatXmlDataSet xmlDataSet = datasetBuilder.build(new FileInputStream(inputXml));
			
			FlatXmlDataSetBuilder datasetBuilderTestDeleteBookWithId1 = new FlatXmlDataSetBuilder();
			FlatXmlDataSet xmlDataSetTestDeleteBookWithId1 = datasetBuilderTestDeleteBookWithId1
					.build(new FileInputStream(testDeleteBookWithId1));
			
			FlatXmlDataSetBuilder datasetBuilderTestEditBookWithId2 = new FlatXmlDataSetBuilder();
			FlatXmlDataSet xmlDataSetTestEditBookWithId2 = datasetBuilderTestEditBookWithId2
					.build(new FileInputStream(testEditBookWithId2));
			
			FlatXmlDataSetBuilder datasetBuilderTestInsertBookWithId3 = new FlatXmlDataSetBuilder();
			FlatXmlDataSet xmlDataSetTestInsertBookWithId3 = datasetBuilderTestInsertBookWithId3
					.build(new FileInputStream(testInsertBookWithId3));
			
			DatabaseOperation.DELETE.execute(databaseConnection, xmlDataSet);
			DatabaseOperation.DELETE.execute(databaseConnection, xmlDataSetTestDeleteBookWithId1);
			DatabaseOperation.DELETE.execute(databaseConnection, xmlDataSetTestEditBookWithId2);
			DatabaseOperation.DELETE.execute(databaseConnection, xmlDataSetTestInsertBookWithId3);
		} catch (Exception error) {
			System.out.println(error);
		}
	}
	
	public static void importData(IDatabaseConnection databaseConnection) {
		try {
			FlatXmlDataSetBuilder datasetBuilder = new FlatXmlDataSetBuilder();
			FlatXmlDataSet xmlDataSet = datasetBuilder.build(new FileInputStream(inputXml));
			DatabaseOperation.INSERT.execute(databaseConnection, xmlDataSet);
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
