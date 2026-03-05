public class FizzBuzz {

    public static String evaluate(int number) {
        if (number % 15 == 0 || (contains(number, 3) && contains(number, 5))) {
            return "FizzBuzz";
        }
        if (number % 3 == 0 || contains(number, 3)) {
            return "Fizz";
        }
        if (number % 5 == 0 || contains(number, 5)) {
            return "Buzz";
        }
        return "" + number;
    }

    private static boolean contains(int number, int digit) {
        return (number + "").indexOf(digit + '0') != -1;
    }
}
