package ge.edu.freeuni.app.service;

import ge.edu.freeuni.app.model.Book;
import ge.edu.freeuni.app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service // marks this as spring managed bean and enables autowired injection into it
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public Book getBookById(int id) {
        return bookRepository.findBookById(id)
            .orElseThrow(() -> new NoSuchElementException("Book not found"));
    }
}
