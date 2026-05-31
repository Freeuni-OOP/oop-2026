package ge.edu.freeuni.app.service;

import ge.edu.freeuni.app.dto.BookResponse;
import ge.edu.freeuni.app.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

import static ge.edu.freeuni.app.util.Converter.toBookResponse;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<BookResponse> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(ge.edu.freeuni.app.util.Converter::toBookResponse)
                .collect(Collectors.toList());
    }

    public BookResponse getBookById(int id) {
        return toBookResponse(bookRepository.findBookById(id).orElseThrow(() -> new NoSuchElementException("Book not found")));
    }
}
