import java.util.*;

public class UnmodifiableExample {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(1, 2, 3);

        List<Integer> unmodifiable = Collections.unmodifiableList(list);
        unmodifiable.set(0, 10); // throws UnsupportedOperationException
    }
}
