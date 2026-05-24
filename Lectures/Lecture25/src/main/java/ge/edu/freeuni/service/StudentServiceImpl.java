package ge.edu.freeuni.service;

import ge.edu.freeuni.dto.StudentRequest;
import ge.edu.freeuni.dto.StudentResponse;
import ge.edu.freeuni.dto.*;
import ge.edu.freeuni.exception.StudentNotFoundException;
import ge.edu.freeuni.model.Student;
import ge.edu.freeuni.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * FEATURE: @Service + @Transactional
 * Business logic lives here, not in the controller or repository.
 *
 * @Slf4j     – Lombok generates a Logger field (log.info / log.error)
 * @Transactional – wraps DB operations in a transaction automatically
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> getAllStudents() {
        log.info("Fetching all students");
        return studentRepository.findAll()
            .stream()
            .map(StudentResponse::from)
            .toList();
    }

    @Override
    @Transactional(readOnly = true)
    public StudentResponse getStudentById(Long id) {
        log.info("Fetching student with id={}", id);
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));
        return StudentResponse.from(student);
    }

    @Override
    @Transactional
    public StudentResponse createStudent(StudentRequest request) {
        log.info("Creating student: {}", request.getEmail());
        Student student = new Student(
            null,
            request.getName(),
            request.getEmail(),
            request.getAge(),
            request.getMajor()
        );
        return StudentResponse.from(studentRepository.save(student));
    }

    @Override
    @Transactional
    public StudentResponse updateStudent(Long id, StudentRequest request) {
        log.info("Updating student id={}", id);
        Student student = studentRepository.findById(id)
            .orElseThrow(() -> new StudentNotFoundException(id));

        student.setName(request.getName());
        student.setEmail(request.getEmail());
        student.setAge(request.getAge());
        student.setMajor(request.getMajor());

        return StudentResponse.from(studentRepository.save(student));
    }

    @Override
    @Transactional
    public void deleteStudent(Long id) {
        log.info("Deleting student id={}", id);
        if (!studentRepository.existsById(id)) {
            throw new StudentNotFoundException(id);
        }
        studentRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<StudentResponse> getStudentsByMajor(Student.Major major) {
        return studentRepository.findByMajor(major)
            .stream()
            .map(StudentResponse::from)
            .toList();
    }
}
