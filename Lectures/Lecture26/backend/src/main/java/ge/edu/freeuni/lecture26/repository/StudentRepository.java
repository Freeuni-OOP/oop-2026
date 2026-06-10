package ge.edu.freeuni.lecture26.repository;

import ge.edu.freeuni.lecture26.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Spring Data JPA repository.
 * All CRUD methods are auto-generated; we only add the custom finder.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {
    List<Student> findByMajor(Student.Major major);
}
