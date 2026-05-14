package dao;

import bean.Student;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

public class StudentDAOImpl implements StudentDAO {

    List<Student> students;

    public StudentDAOImpl() {
        this.students = new Vector<>();
    }

    @Override
    public void addStudent(Student student) {
        this.students.add(student);
    }

    @Override
    public Student getStudentById(int id) {
        for (Student student : students) {
            if (student.getId() == id) {
                return student;
            }
        }

        return null;
    }

    @Override
    public void removeStudent(Student student) {
        this.students.remove(student);
    }

    @Override
    public List<Student> getAllStudents() {
        return Collections.unmodifiableList(this.students);
    }

}