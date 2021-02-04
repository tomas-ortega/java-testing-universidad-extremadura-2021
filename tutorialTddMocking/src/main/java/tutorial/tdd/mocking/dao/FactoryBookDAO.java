package tutorial.tdd.mocking.dao;

public class FactoryBookDAO {
	private FactoryBookDAO() {
		// Private to prevent create object from FactoryBookDAO
	}

	public static InterfacesDAO getBookDAO() {
		return new BookDAO();
	}
}
