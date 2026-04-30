package dao;

import bean.Student;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class JdbcStudentDAO implements StudentDAO {
    private BasicDataSource dataSource;

    public JdbcStudentDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public void addStudent(Student student) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("insert into students (first_name, last_name) values (?, ?)");
            statement.setString(1, student.getFirstName());
            statement.setString(2, student.getLastName());
            statement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Student> getAllStudents() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("select * from students");
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                students.add(new Student(resultSet.getString("first_name"), resultSet.getString("last_name")));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
