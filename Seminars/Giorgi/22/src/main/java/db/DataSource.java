package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DataSource {

    private static final Logger LOGGER = Logger.getLogger(DataSource.class.getName());

    private static final String URL = "jdbc:mysql://"
            + System.getenv().getOrDefault("DB_HOST", "localhost")
            + ":3306/"
            + System.getenv().getOrDefault("DB_NAME", "book_db");
    private static final String USER = System.getenv().getOrDefault("DB_USER", "root");
    private static final String PASSWORD = System.getenv().getOrDefault("DB_PASSWORD", "root");

    private static volatile DataSource instance;

    private DataSource() {
        int retries = 10;
        while (retries-- > 0) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                try (Connection connection = getConnection();
                     Statement statement = connection.createStatement()) {
                    statement.executeUpdate(
                            "CREATE TABLE IF NOT EXISTS books (" +
                                    "  id    INT PRIMARY KEY AUTO_INCREMENT," +
                                    "  title VARCHAR(255) NOT NULL" +
                                    ")"
                    );
                }
                return;
            } catch (SQLException | ClassNotFoundException e) {
                LOGGER.log(Level.WARNING, "DB not ready, retrying... (" + retries + " left)");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ignored) {
                }
            }
        }
        LOGGER.log(Level.SEVERE, "Failed to connect to database after all retries");
    }

    public static DataSource getInstance() {
        if (instance == null) {
            synchronized (DataSource.class) {
                if (instance == null) {
                    instance = new DataSource();
                }
            }
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
