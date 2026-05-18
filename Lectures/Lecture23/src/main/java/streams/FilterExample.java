package streams;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class FilterExample {

    public static void main(String[] args) {
        List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5);

        System.out.println(getOdds(numbers));
    }

    public static List<Integer> getOdds(List<Integer> list) {
        return list.stream().filter(elem -> elem % 2 == 1).collect(Collectors.toList());
    }
}
