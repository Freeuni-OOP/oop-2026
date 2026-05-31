package ge.edu.freeuni.app.util;

import ge.edu.freeuni.app.dto.BookResponse;
import ge.edu.freeuni.app.model.Book;

public class Converter {

    public static BookResponse toBookResponse(Book book) {
        return new BookResponse(book.getId(), book.getTitle());
    }
}
