public class BooleanFilter implements Filter {
    private boolean[] indexes;
    private int count;

    public BooleanFilter() {
        indexes = new boolean[8];
        count = 0;
    }

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isPresent(int index) {
        if (index >= indexes.length)
            return false;
        return indexes[index];
    }

    @Override
    public void add(int index) {
        if (index >= indexes.length) {
            int newSize = Math.max(index + 1, indexes.length * 2);
            boolean[] newIndexes = new boolean[newSize];
            System.arraycopy(indexes, 0, newIndexes, 0, indexes.length);
            indexes = newIndexes;
        }
        if (!indexes[index]) {
            count++;
            indexes[index] = true;
        }
    }

    @Override
    public void remove(int index) {
        if (index >= indexes.length)
            return;
        if (indexes[index]) {
            indexes[index] = false;
            count--;
        }
    }
}