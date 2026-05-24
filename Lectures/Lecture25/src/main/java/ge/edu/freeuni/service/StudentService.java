package ge.edu.freeuni.service;

import ge.edu.freeuni.dto.StudentRequest;
import ge.edu.freeuni.dto.StudentResponse;
import ge.edu.freeuni.model.Student;

import java.util.List;

/**
 * FEATURE: Service interface
 * Defines the contract – implementation is in StudentServiceImpl.
 * Programming to interfaces makes mocking in tests easy.
 */
public interface StudentService {

    List<StudentResponse> getAllStudents();

    StudentResponse getStudentById(Long id);

    StudentResponse createStudent(StudentRequest request);

    StudentResponse updateStudent(Long id, StudentRequest request);

    void deleteStudent(Long id);

    List<StudentResponse> getStudentsByMajor(Student.Major major);
}
