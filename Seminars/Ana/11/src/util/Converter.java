package src.util;

import src.model.Course;
import src.model.Student;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Converter {
    public static Student toStudent(ResultSet resultSet) throws SQLException {
        return new Student(
            resultSet.getLong("id"),
            resultSet.getLong("student_id"),
            resultSet.getLong("idnumber"),
            resultSet.getString("firstname"),
            resultSet.getString("lastname"),
            resultSet.getDate("register_date")
        );
    }

    public static Course toCourse(ResultSet results) throws SQLException {
        return new Course(
            results.getLong("id"),
            results.getLong("course_id"),
            results.getString("course_name"),
            results.getInt("course_credit"),
            results.getString("course_type")
        );
    }
}