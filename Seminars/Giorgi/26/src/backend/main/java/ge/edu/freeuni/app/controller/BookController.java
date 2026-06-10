package ge.edu.freeuni.app.controller;

import ge.edu.freeuni.app.dto.BookResponse;
import ge.edu.freeuni.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }
}
