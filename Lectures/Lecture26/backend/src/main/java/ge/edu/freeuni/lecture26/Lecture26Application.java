package ge.edu.freeuni.lecture26;

import ge.edu.freeuni.lecture26.model.Student;
import ge.edu.freeuni.lecture26.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

/**
 * LECTURE 26 – React + Spring Boot Full-Stack Integration
 * <p>
 * What is new compared to Lecture 25?
 * 1. Thymeleaf removed → pure REST API (JSON only)
 * 2. CORS configured → React (port 3000) can call Spring (port 8082)
 * 3. Basic Auth headers sent from React on write operations
 * 4. Credentials stored in browser sessionStorage (demo) – JWT is the prod way
 * <p>
 * Run order:
 * 1. docker compose up -d           (starts MySQL on 3308)
 * 2. mvn spring-boot:run             (starts backend on 8082)
 * 3. cd frontend && npm install && npm start  (starts React on 3000)
 */
@SpringBootApplication
@Slf4j
public class Lecture26Application {

    public static void main(String[] args) {
        SpringApplication.run(Lecture26Application.class, args);
    }

    /**
     * Seed a few students so the React list is not empty on first run
     */
    @Bean
    CommandLineRunner seed(StudentRepository repo) {
        return args -> {
            if (repo.count() == 0) {
                repo.save(new Student(null, "Alice Johnson", "alice@freeuni.edu", 20, Student.Major.COMPUTER_SCIENCE));
                repo.save(new Student(null, "Bob Smith", "bob@freeuni.edu", 22, Student.Major.MATHEMATICS));
                repo.save(new Student(null, "Carol Williams", "carol@freeuni.edu", 21, Student.Major.PHYSICS));
                repo.save(new Student(null, "David Brown", "david@freeuni.edu", 23, Student.Major.BUSINESS));
                log.info("✅ Seeded 4 demo students");
            }
        };
    }
}

