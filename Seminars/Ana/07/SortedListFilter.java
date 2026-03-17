import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortedListFilter implements Filter {
    private List<Integer> indexes;

    public SortedListFilter() {
        indexes = new ArrayList<>();
    }

    @Override
    public int size() {
        return indexes.size();
    }

    @Override
    public boolean isPresent(int index) {
        return Collections.binarySearch(indexes, index) >= 0;
    }

    @Override
    public void add(int index) {
        int pos = Collections.binarySearch(indexes, index);
        if (pos < 0) {
            indexes.add(-pos - 1, index);
        }
    }

    @Override
    public void remove(int index) {
        int pos = Collections.binarySearch(indexes, index);
        if (pos >= 0) {
            indexes.remove(pos);
        }
    }
}