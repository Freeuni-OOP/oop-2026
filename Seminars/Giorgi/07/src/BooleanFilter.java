public class BooleanFilter implements Filter {

    // invariant: index = true <-> index is present

    private boolean[] elements;

    private int numPresents;

    public BooleanFilter() {
        this.elements = new boolean[4];
        this.numPresents = 0;
    }

    @Override
    public int size() {
        return this.numPresents;
    }

    @Override
    public boolean isPresent(int index) {
        return index < this.elements.length && this.elements[index];
    }

    @Override
    public void add(int index) {
        if (isPresent(index)) {
            return;
        }

        if (index >= elements.length) {
            int newSize = Math.max(index + 1, 2 * this.elements.length);
            grow(newSize);
        }
        elements[index] = true;
        this.numPresents++;
    }

    private void grow(int newSize) {
        boolean[] newElements = new boolean[newSize];
        System.arraycopy(elements, 0, newElements, 0, this.elements.length);
        elements = newElements;
    }

    @Override
    public void remove(int index) {
        if (!isPresent(index)) {
            return;
        }
        elements[index] = false;
        this.numPresents--;
    }
}
