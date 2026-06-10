package ge.edu.freeuni.app.repository;

import ge.edu.freeuni.app.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository<Book, Long> {

    Optional<Book> findBookById(int id);
}
