import java.sql.*;

public class Main {

    private static final String USER_NAME = "root";
    private static final String PASSWORD = "root";
    private static final String DB = "students_db";

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