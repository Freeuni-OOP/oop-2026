package solid.s.good;

// business logic
public class BookService {

    private final Repository repository;

    public BookService(Repository repository) {
        this.repository = repository;
    }

    public void printContent(GoodBook book) {
        System.out.println("name: " + book.getName() + ", content: " + book.getContent());
    }

    public void addBook(GoodBook book) {
        repository.saveToDatabase(book);
    }
}
