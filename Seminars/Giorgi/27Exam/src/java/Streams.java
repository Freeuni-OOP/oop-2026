import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

public class Streams {

    public static void main(String[] args) {
        List<Integer> items = Arrays.asList(1, 2, 2, 1, 2, 2, 2);

        System.out.println(removeConsecutiveDuplicates(items.stream(), (a,  b) -> a.equals(b) ? 0 : 1 ));
    }

    static <T> List<T> removeConsecutiveDuplicates(Stream<T> items, Comparator<T> cmp) {
        return items
                .reduce(new ArrayList<>(),
                        (list, element) -> {
                            if (list.isEmpty() || cmp.compare(list.getLast(), element) != 0){
                                list.add(element);
                            }
                            return list;
                        },
                        (curr, next) -> curr);
    }
}
