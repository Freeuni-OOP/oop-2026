package dao;

import bean.Student;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MySQLStudentDAO implements StudentDAO {

    private final DataSource dataSource;

    public MySQLStudentDAO(DataSource dataSource) throws SQLException {
        this.dataSource = dataSource;
        createTableIfNeeded();
    }

    @Override
    public void addStudent(Student student) {
        try (Connection con = dataSource.getConnection()) {
            PreparedStatement statement = con.prepareStatement("INSERT INTO student (first_name, last_name) values (?, ?)");
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void createTableIfNeeded() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS student ("
                + "id INT AUTO_INCREMENT PRIMARY KEY,"
                + "first_name VARCHAR(255) NOT NULL,"
                + "last_name VARCHAR(255) NOT NULL"
                + ")";

        try (Connection connection = dataSource.getConnection();
             Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try (Connection con = dataSource.getConnection()) {
            Statement statement = con.createStatement();

            ResultSet resultSet = statement.executeQuery("SELECT * FROM student;");

            List<Student> students = new ArrayList<>();

            while (resultSet.next()) {
                students.add(new Student(
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));
            }

            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
