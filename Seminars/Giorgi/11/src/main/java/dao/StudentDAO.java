package dao;

import bean.Course;
import bean.Student;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static util.Converter.toCourse;
import static util.Converter.toStudent;

public class StudentDAO {

    private final BasicDataSource dataSource;

    public StudentDAO(BasicDataSource dataSource) {
        this.dataSource = dataSource;
    }

    public List<Student> getAllStudents() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet results = statement.executeQuery("SELECT * FROM Students");

            List<Student> students = new ArrayList<>();

            while (results.next()) { // row-by-row iteration
                students.add(toStudent(results));
            }

            return students;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    public List<Course> getStudentCourses(int studentId) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement("select c.*\n" +
                    "from courses c\n" +
                    "left join student_courses sc on c.id = sc.course_id\n" +
                    "where sc.student_id = ?;");
            statement.setInt(1, studentId);

            ResultSet results = statement.executeQuery();

            List<Course> courses = new ArrayList<>();

            while (results.next()) {
                courses.add(toCourse(results));
            }

            return courses;
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}
