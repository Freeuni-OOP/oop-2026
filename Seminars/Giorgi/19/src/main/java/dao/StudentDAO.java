package dao;

import bean.Student;

import java.util.List;

public interface StudentDAO {

    void addStudent(Student student);

    List<Student> getAllStudents();
}
