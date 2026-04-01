package src.dao;

import org.apache.commons.dbcp2.BasicDataSource;
import src.model.Course;
import src.model.Student;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import static src.util.Converter.toCourse;
import static src.util.Converter.toStudent;


public class StudentDAO {
    private BasicDataSource basicDataSource;

    public StudentDAO(BasicDataSource basicDataSource) {
        this.basicDataSource = basicDataSource;
    }

    public List<Student> getAllStudents() {
        try (Connection connection = basicDataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Students");
            List<Student> students = new ArrayList<>();
            while (results.next()) {
                students.add(toStudent(results));
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Course> getStudentCourses(int studentId) {
        try (Connection connection = basicDataSource.getConnection()) {
            PreparedStatement preparedStatement =
                connection.prepareStatement("SELECT * FROM courses c left join student_courses sc on c.id = sc.course_id where sc.student_id = ?;");
            preparedStatement.setInt(1, studentId);
            List<Course> courses = new ArrayList<>();
            ResultSet results = preparedStatement.executeQuery();
            while (results.next()) {
                courses.add(toCourse(results));
            }
            return courses;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}