import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> l1 = Arrays.asList(1, 2, 3);
        List<Double> l2 = Arrays.asList(1.0, 2.0, 3.0);
        System.out.println(getFirst(l1, l2));

        System.out.println(getFirstSimple(1, "1"));
    }

    // OK
    private static Object getFirst(List<?> first, List<?> second) {
        return first.get(0);
    }

    // still OK
    private static <T> T getFirstSimple(T first, T second) {
        return first;
    }

//    BREAKS!!!
//    private static <T> T getFirst(List<T> first, List<T> second) {
//        return first.get(0);
//    }
}
