package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MapExample {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3);

        System.out.println(doubleValues(numbers));
    }

    public static List<Integer> doubleValues(List<Integer> list) {
        return list.stream().map(elem -> 2 * elem).collect(Collectors.toList());
    }
}
