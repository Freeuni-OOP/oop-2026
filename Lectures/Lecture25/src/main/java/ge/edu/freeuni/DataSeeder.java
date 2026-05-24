package ge.edu.freeuni;

import ge.edu.freeuni.model.Student;
import ge.edu.freeuni.repository.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * FEATURE: CommandLineRunner
 * Runs once after the application context is fully loaded.
 * Used here to seed the in-memory H2 database with sample data.
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class DataSeeder implements CommandLineRunner {

    private final StudentRepository studentRepository;

    @Override
    public void run(String... args) {
        // Only seed if the table is empty — avoids duplicate key errors on restart
        if (studentRepository.count() > 0) {
            log.info("Students already exist ({} rows), skipping seed.", studentRepository.count());
            return;
        }

        log.info("Seeding sample students...");

        studentRepository.saveAll(List.of(
            new Student(null, "Alice Johnson",  "alice@freeuni.edu.ge",  20, Student.Major.COMPUTER_SCIENCE),
            new Student(null, "Bob Smith",      "bob@freeuni.edu.ge",    22, Student.Major.MATHEMATICS),
            new Student(null, "Carol Williams", "carol@freeuni.edu.ge",  19, Student.Major.PHYSICS),
            new Student(null, "David Brown",    "david@freeuni.edu.ge",  21, Student.Major.BUSINESS),
            new Student(null, "Eve Davis",      "eve@freeuni.edu.ge",    23, Student.Major.COMPUTER_SCIENCE)
        ));

        log.info("Seeded {} students.", studentRepository.count());
    }
}
