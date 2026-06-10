package ge.edu.freeuni.app;

import ge.edu.freeuni.app.model.Book;
import ge.edu.freeuni.app.repository.BookRepository;
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

    private final BookRepository bookRepository;

    @Override
    public void run(String... args) {
        // Only seed if the table is empty — avoids duplicate key errors on restart
        if (bookRepository.count() > 0) {
            log.info("Books already exist ({} rows), skipping seed.", bookRepository.count());
            return;
        }

        log.info("Seeding sample students...");

        bookRepository.saveAll(List.of(
                new Book("book1"),
                new Book("book2"),
                new Book("book3")
        ));

        log.info("Seeded {} students.", bookRepository.count());
    }
}
