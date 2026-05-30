package ge.edu.freeuni.lecture26.service;

import ge.edu.freeuni.lecture26.dto.StudentRequest;
import ge.edu.freeuni.lecture26.dto.StudentResponse;
import ge.edu.freeuni.lecture26.exception.DuplicateEmailException;
import ge.edu.freeuni.lecture26.exception.ResourceNotFoundException;
import ge.edu.freeuni.lecture26.model.Student;
import ge.edu.freeuni.lecture26.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * FEATURE: Service Layer
 * <p>
 * All business logic lives here, keeping controllers thin.
 * Converts Entity ↔ DTO so the API never leaks internal structure.
 */
@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentRepository repo;

    public List<StudentResponse> getAllStudents() {
        return repo.findAll().stream().map(StudentResponse::from).toList();
    }

    public StudentResponse getStudentById(Long id) {
        return StudentResponse.from(findOrThrow(id));
    }

    public List<StudentResponse> getByMajor(Student.Major major) {
        return repo.findByMajor(major).stream().map(StudentResponse::from).toList();
    }

    public StudentResponse createStudent(StudentRequest req) {
        if (repo.findAll().stream().anyMatch(s -> s.getEmail().equalsIgnoreCase(req.getEmail()))) {
            throw new DuplicateEmailException("Email already in use: " + req.getEmail());
        }
        Student s = new Student(null, req.getName(), req.getEmail(), req.getAge(), req.getMajor());
        return StudentResponse.from(repo.save(s));
    }

    public StudentResponse updateStudent(Long id, StudentRequest req) {
        Student s = findOrThrow(id);
        s.setName(req.getName());
        s.setEmail(req.getEmail());
        s.setAge(req.getAge());
        s.setMajor(req.getMajor());
        return StudentResponse.from(repo.save(s));
    }

    public void deleteStudent(Long id) {
        repo.delete(findOrThrow(id));
    }

    private Student findOrThrow(Long id) {
        return repo.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Student not found with id: " + id));
    }
}
