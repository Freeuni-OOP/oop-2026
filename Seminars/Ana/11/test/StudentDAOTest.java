package test;

import org.apache.commons.dbcp2.BasicDataSource;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import src.dao.StudentDAO;
import src.model.Course;
import src.model.Student;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentDAOTest {

    private static BasicDataSource dataSource;

    private StudentDAO dao;

    @BeforeAll
    public static void setUpH2() throws SQLException {
        dataSource = new BasicDataSource();
        dataSource.setUrl("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1");

        Connection connection = dataSource.getConnection();
        Statement statement = connection.createStatement();

        createTables(statement);
        insertData(statement);
    }

    private static void createTables(Statement statement) throws SQLException {
        statement.execute("CREATE TABLE courses\n" +
            "(\n" +
            "    id            INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "    course_id     INT          NOT NULL,\n" +
            "    course_name   VARCHAR(255) NOT NULL,\n" +
            "    course_credit INT          NOT NULL DEFAULT 0,\n" +
            "    course_type   VARCHAR(255),\n" +
            "    CONSTRAINT courses_uk UNIQUE (course_id)\n" +
            ");");
        statement.execute("CREATE TABLE students\n" +
            "(\n" +
            "    id            INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "    student_id    INT          NOT NULL,\n" +
            "    idnumber      CHAR(11)     NOT NULL,\n" +
            "    firstname     VARCHAR(100) NOT NULL,\n" +
            "    lastname      VARCHAR(100) NOT NULL,\n" +
            "    register_date DATETIME     NOT NULL DEFAULT NOW(),\n" +
            "    CONSTRAINT students_uk1 UNIQUE (student_id),\n" +
            "    CONSTRAINT students_uk2 UNIQUE (idnumber)\n" +
            ");");
        statement.execute("CREATE TABLE student_courses\n" +
            "(\n" +
            "    id         INT PRIMARY KEY AUTO_INCREMENT,\n" +
            "    course_id  INT NOT NULL,\n" +
            "    student_id INT NOT NULL,\n" +
            "    CONSTRAINT student_courses_uk1 UNIQUE (course_id, student_id),\n" +
            "    CONSTRAINT student_courses_fk1 FOREIGN KEY (course_id)\n" +
            "        REFERENCES courses (id),\n" +
            "    CONSTRAINT student_courses_fk2 FOREIGN KEY (student_id)\n" +
            "        REFERENCES students (id)\n" +
            ");");
    }

    private static void insertData(Statement statement) throws SQLException {
        statement.execute("insert into students (student_id, idnumber, firstName, lastName)\n" +
            "values (1, '11111111111', 'temur', 'arustashvili'),\n" +
            "       (2, '11111111112', 'giorgi', 'adikashvili'),\n" +
            "       (3, '11111111113', 'ana', 'sepiashvili');\n");
        statement.execute("INSERT INTO courses (course_id, course_name, course_credit, course_type)\n" +
            "VALUES (1, 'Linear Algebra', 4, 'MATH'),\n" +
            "       (2, 'Programming Paradigms', 6, 'CS'),\n" +
            "       (3, 'Calculus I', 6, 'MATH'),\n" +
            "       (4, 'OOP', 6, 'CS'),\n" +
            "       (5, 'Quantum Algorithms', 7, 'CS'),\n" +
            "       (6, 'Nand To Tetris', 6, 'CS');");
        statement.execute("insert into student_courses (student_id, course_id)\n" +
            "select s.id, c.id\n" +
            "from students s\n" +
            "inner join courses c\n" +
            "where (s.firstname = 'temur' and c.course_credit = 7)\n" +
            "   or (s.firstname = 'temur' and c.course_credit = 4);");
    }

    @BeforeEach
    public void setUpDAO() {
        dao = new StudentDAO(dataSource);
    }

    @Test
    public void testGetAllStudents() {
        List<Student> students = dao.getAllStudents();
        assertEquals(3, students.size());
        assertEquals("temur", students.get(0).getFirstname());
    }

    @Test
    public void testGetStudentCourses() {
        List<Course> courses = dao.getStudentCourses(1);
        assertEquals(2, courses.size());
        assertEquals(7, courses.get(1).getCourse_credit());
    }

}