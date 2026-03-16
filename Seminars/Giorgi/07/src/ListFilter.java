import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ListFilter implements Filter {

    // invariant: indexes is sorted

    private List<Integer> indexes;

    public ListFilter() {
        this.indexes = new ArrayList<>();
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
        if (isPresent(index)) {
            return;
        }

        int i = -Collections.binarySearch(indexes, index) - 1;

        indexes.add(i, index);
    }

    @Override
    public void remove(int index) {
        if (!isPresent(index)) {
            return;
        }
        indexes.remove((Integer) index);
    }
}
