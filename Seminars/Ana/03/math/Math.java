package math;

public class Math {

    public static int add(int number1, int number2) {
        return number1 + number2;
    }

    public static int abs(int number) {
        if (number == Integer.MIN_VALUE) {
            throw new IllegalArgumentException();
        }
        return number < 0 ? -number : number;
    }
}