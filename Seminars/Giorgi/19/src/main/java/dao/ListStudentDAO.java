package dao;

import bean.Student;
import filter.Filter;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListStudentDAO implements StudentDAO {

    private final List<Student> students = new CopyOnWriteArrayList<>();

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(students);
    }

    @Override
    public List<Student> filterStudents(Filter filter) {
        return Collections.emptyList();
    }
}
