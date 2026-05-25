package ge.edu.freeuni.app.service;

import ge.edu.freeuni.app.dto.BookResponse;
import ge.edu.freeuni.app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

import static ge.edu.freeuni.app.util.Converter.toBookResponse;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public BookResponse getBookById(int id) {
        return toBookResponse(bookRepository.findBookById(id).orElseThrow(() -> new NoSuchElementException("Book not found")));
    }
}
