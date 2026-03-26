package solid.s.good;

public class BookPrinter {
    public void printBook(Book book) {
        System.out.println("Book: " + book.getName());
        System.out.println(book.getContent());
    }
}