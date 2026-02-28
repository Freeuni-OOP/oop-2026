import java.util.List;

public class Pair<T> {
    // instance variables
    private T a; // Can declare T variables
    private T b;
    private List<T> unused; // Can use T like this too

    public Pair(T a, T b) {
        this.a = a;
        this.b = b;
    }

    public T getA() {
        return a;
    }

    public T getB() {
        return b;
    }

    public void swap() {
        T temp = a; // NOTE T temporary variable
        a = b;
        b = temp;
    }

    public boolean isSame() {
        return a.equals(b);
    }

    // NOTE: use of T as a parameter
    public boolean contains(T elem) {
        return (a.equals(elem) || b.equals(elem));
    }

    // Show things that do not work with T
    private void doesNotWork(Object x) {
        // T var = new T(); // NO, T not real at runtime -- erasure
        // T[] array = new T[10]; // NO, same reason
        // T temp = (T) x; // NO, same reason (like (Object) cast)
    }
}