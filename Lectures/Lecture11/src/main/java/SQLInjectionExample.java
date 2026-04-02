import java.sql.*;

public class SQLInjectionExample {

    private static final String USER_NAME = "root"; // replace with your user
    private static final String PASSWORD = "root"; // replace with your password
    private static final String DB = "students_db"; // replace with your db

    public static void main(String[] args) {
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/" + DB,
                    USER_NAME,
                    PASSWORD
            );

            // get student by first name
            String firstName = "'giorgi' or true"; // sql injection risk
            String statement = "SELECT * FROM Students where firstname = ?";
            PreparedStatement preparedStatement = con.prepareStatement(statement);
            preparedStatement.setString(1, firstName);

            ResultSet result = preparedStatement.executeQuery();

            while (result.next()) {
                System.out.println(result.getString("firstname")
                        + " " + result.getString("lastname"));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
