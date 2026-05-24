package ge.edu.freeuni.repository;

import ge.edu.freeuni.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

/**
 * FEATURE: Spring Data JPA Repository
 * Extend JpaRepository to get CRUD for free.
 * Add method signatures – Spring generates the SQL automatically.
 */
public interface StudentRepository extends JpaRepository<Student, Long> {

    // Derived query: SELECT * FROM students WHERE email = ?
    Optional<Student> findByEmail(String email);

    // Derived query: SELECT * FROM students WHERE major = ?
    List<Student> findByMajor(Student.Major major);

    // Custom JPQL query
    @Query("SELECT s FROM Student s WHERE s.age >= :minAge")
    List<Student> findStudentsOlderThan(int minAge);
}
