public class PairNumber<T extends Number> {
    private final T a;
    private final T b;

    public PairNumber(T a, T b) {
        this.a = a;
        this.b = b;
    }

    public int sum() {
        // Note: here we can use Number messages, like intValue().
        return (a.intValue() + b.intValue());
    }
}