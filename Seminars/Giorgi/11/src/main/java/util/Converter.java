package util;

import bean.*;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Converter {

    public static Student toStudent(ResultSet results) throws SQLException {
        return new Student(
                results.getLong("id"),
                results.getLong("student_id"),
                results.getLong("idnumber"),
                results.getString("firstname"),
                results.getString("lastname"),
                results.getDate("register_date")
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
