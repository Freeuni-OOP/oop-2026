import java.util.Collection;

public class MyStack<T> {

    private Object[] elements;

    private int logLen;

    public MyStack() {
        elements = new Object[4];
        logLen = 0;
    }

    public void add(T element) {
        if (logLen == elements.length) {
            grow();
        }
        elements[logLen++] = element;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (logLen == 0) {
            throw new IndexOutOfBoundsException("Empty Stack");
        }
        return (T) elements[--logLen];
    }

    public void addAll(Collection<T> elements) {
        for (T element : elements) {
            add(element);
        }
    }

    public int size() {
        return logLen;
    }

    private void grow() {
        Object[] newElements = new Object[2 * elements.length];
        System.arraycopy(elements, 0, newElements, 0, elements.length);
        elements = newElements;
    }
}
