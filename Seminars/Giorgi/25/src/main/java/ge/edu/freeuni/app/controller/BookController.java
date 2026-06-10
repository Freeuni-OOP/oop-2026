package ge.edu.freeuni.app.controller;

import ge.edu.freeuni.app.dto.BookResponse;
import ge.edu.freeuni.app.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/{id}")
    public BookResponse getBookById(@PathVariable int id) {
        return bookService.getBookById(id);
    }
}
