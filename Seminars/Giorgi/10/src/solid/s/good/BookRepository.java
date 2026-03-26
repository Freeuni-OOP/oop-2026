package solid.s.good;

import java.util.ArrayList;
import java.util.List;

public class BookRepository implements Repository {
    private final List<GoodBook> books = new ArrayList<>();

    @Override
    public void saveToDatabase(GoodBook book) {
        books.add(book);
    }
}
