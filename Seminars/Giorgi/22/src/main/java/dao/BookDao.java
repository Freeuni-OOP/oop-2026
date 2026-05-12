package dao;

import db.DataSource;
import model.Book;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookDao {

    public Book findById(int id) throws SQLException {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "SELECT id, title FROM books WHERE id = ?"
             )) {

            ps.setInt(1, id);

            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Book(rs.getInt("id"), rs.getString("title"));
                }
            }
        }

        return null;
    }

    public void insert(String title) throws SQLException {
        try (Connection connection = DataSource.getInstance().getConnection();
             PreparedStatement ps = connection.prepareStatement(
                     "INSERT INTO books (title) VALUES (?)"
             )) {
            ps.setString(1, title);
            ps.executeUpdate();
        }
    }
}
