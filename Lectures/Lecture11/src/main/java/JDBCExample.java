import java.sql.*;

public class JDBCExample {

    private static final String USER_NAME = "root"; // replace with your user
    private static final String PASSWORD = "root"; // replace with your password
    private static final String DB = "students_db"; // replace with your db

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + DB,
                    USER_NAME,
                    PASSWORD
            );

            Statement statement = con.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Students");

            while (results.next()) {
                System.out.println(results.getString("firstname")
                        + " " + results.getString("lastname"));
            }

        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }
}