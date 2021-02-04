package tutorial.tdd.mocking.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import tutorial.tdd.mocking.domain.BookDTO;

public class BookDAO implements InterfacesDAO {

	@Override
	public void insert(BookDTO book, Connection connectionReference) throws SQLException {
		StringBuilder insertSql = new StringBuilder();
		PreparedStatement pstm = null;
		
		try {
			insertSql.append("INSERT INTO ");
			insertSql.append("book");
			insertSql.append("(id,title,author,editorial,pages)");
			insertSql.append(" VALUES (?,?,?,?,?)");
			pstm = connectionReference.prepareStatement(insertSql.toString());
			pstm.setInt(1, book.getId());
			pstm.setString(2, book.getTitle());
			pstm.setString(3, book.getAuthor());
			pstm.setString(4, book.getEditorial());
			pstm.setInt(5, book.getPages());
			pstm.execute();
		} catch (SQLException e) {
			throw e;
		}
	}

	@Override
	public BookDTO searchBookById(Integer bookId, Connection connectionReference) throws SQLException {
		PreparedStatement pstm = null;
		StringBuilder selectSql = null;
		ResultSet resultSet = null;
		BookDTO libro = null;
		try {
			selectSql = new StringBuilder();
			selectSql.append("SELECT id, title, author, editorial, pages FROM book WHERE id = ?");
			pstm = connectionReference.prepareStatement(selectSql.toString());
			pstm.setInt(1, bookId);
			resultSet = pstm.executeQuery();
			while (resultSet.next()) {
				libro = new BookDTO(resultSet.getInt("id"), resultSet.getString("title"), resultSet.getString("author"),
						resultSet.getString("editorial"), resultSet.getInt("pages"));
			}
		} catch (SQLException e) {
			throw e;
		}
		return libro;
	}
}
