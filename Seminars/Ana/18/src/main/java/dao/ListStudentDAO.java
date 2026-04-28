package dao;

import bean.Student;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ListStudentDAO implements StudentDAO {
    private List<Student> students = new CopyOnWriteArrayList<>();

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(students);
    }
}
