package ge.edu.freeuni.dao;

import ge.edu.freeuni.dto.UserDTO;
import ge.edu.freeuni.model.User;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDao {

    private final DataSource dataSource;

    public UserDao(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        createTableIfNeeded();
    }

    public void save(User user) throws SQLException {
        String sql = "INSERT INTO users(name) VALUES(?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, user.getName());
            statement.executeUpdate();
        }
    }

    public List<UserDTO> getUsers() throws SQLException {
        String sql = "select * from users";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {

            ResultSet users = statement.executeQuery(sql);
            List<UserDTO> result = new ArrayList<>();

            while (users.next()) {
                result.add(new UserDTO(users.getString("name")));
            }

            return result;
        }
    }

    private void createTableIfNeeded() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS users ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "name VARCHAR(255) NOT NULL"
                + ")";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }
}
