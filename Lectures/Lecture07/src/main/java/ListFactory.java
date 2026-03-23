import java.util.ArrayList;
import java.util.List;

public interface ListFactory {

    static <T> List<T> getList() {
        return new ArrayList<>();
    }
}
