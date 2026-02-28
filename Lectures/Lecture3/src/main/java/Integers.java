import java.util.Arrays;
import java.util.List;

public class Integers {

    public static void main(String[] args) {
        List<Integer> list = Arrays.asList(127, 127);

        System.out.println(list.get(0) == list.get(1));
    }
}
