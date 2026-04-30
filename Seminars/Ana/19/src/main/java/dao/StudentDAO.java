package dao;

import bean.Student;

import java.util.List;

public interface StudentDAO {
    public void addStudent(Student student);

    public List<Student> getAllStudents();
}
