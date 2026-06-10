package functions;

import java.util.Scanner;
import java.util.function.Function;

public class ApplyTwice {

    static int applyTwice(Function<Integer, Integer> func, int x) {
        return func.apply(func.apply(x));
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number: ");
        int number = scanner.nextInt();
        System.out.println();

        System.out.print("Enter factor: ");
        int factor = scanner.nextInt();
        System.out.println();

        int result = applyTwice(
                n -> n * factor,
                number
        );

        System.out.println("Result: " + result);
    }
}