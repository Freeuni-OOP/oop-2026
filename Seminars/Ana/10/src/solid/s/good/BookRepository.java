package solid.s.good;

public class BookRepository {
    public void saveToDatabase(Book book) {
        // saving logic
        System.out.println("Saving Book " + book.getName() + " to database");
    }
}