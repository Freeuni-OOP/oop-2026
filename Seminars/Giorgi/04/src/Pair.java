import java.util.Objects;

public class Pair<T extends Number, G extends String> {

    private T first;

    private G second;

    public Pair(T first, G second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public G getSecond() {
        return second;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public void setSecond(G second) {
        this.second = second;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }
}
