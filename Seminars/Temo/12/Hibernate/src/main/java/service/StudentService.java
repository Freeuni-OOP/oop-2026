package service;

import dao.StudentRepository;
import model.Student;

public class StudentService {

  private final StudentRepository studentRepository;

  public StudentService(StudentRepository studentRepository) {
    this.studentRepository = studentRepository;
  }

  public void saveStudent(Student student) {
    studentRepository.save(student);
  }

  public Student findStudentById(long id) {
    Student student = studentRepository.findById(id);
    if (student == null) {
      throw new RuntimeException("Student not found with id: " + id);
    }
    return student;
  }

  public void deleteStudentById(long id) {
    studentRepository.deleteById(id);
  }
}
