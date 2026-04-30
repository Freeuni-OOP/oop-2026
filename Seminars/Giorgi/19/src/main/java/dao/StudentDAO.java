package dao;

import bean.Student;
import filter.Filter;

import java.util.List;

public interface StudentDAO {

    void addStudent(Student student);

    List<Student> getAllStudents();

    List<Student> filterStudents(Filter filter);
}
