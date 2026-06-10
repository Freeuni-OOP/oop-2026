package streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class ParallelStreamExample {

    public static void main(String[] args) {
        List<Integer> list = IntStream.rangeClosed(1, 20)
                .boxed()
                .collect(Collectors.toList());

        System.out.println("forEach (parallel):");
        list.parallelStream()
                .forEach(i ->
                        System.out.println(i + " - " + Thread.currentThread().getName())
                );

        System.out.println("\nforEachOrdered:");
        list.stream().forEach(i ->
                System.out.println(i + " - " + Thread.currentThread().getName())
        );
    }
}
