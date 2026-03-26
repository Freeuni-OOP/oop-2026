package solid.s.good;

public class Main {
    public static void main(String[] args) {
        Book book = new Book("Harry Potter", "content..", 1999);

        BookPrinter bookPrinter = new BookPrinter();
        bookPrinter.printBook(book);

        BookRepository bookRepository = new BookRepository();
        bookRepository.saveToDatabase(book);
    }
}