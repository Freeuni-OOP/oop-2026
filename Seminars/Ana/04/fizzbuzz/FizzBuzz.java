package fizzbuzz;

public class FizzBuzz {
    public String evaluate(int number) {
        if (number % 15 == 0 || (containsDigit(number, 3) && containsDigit(number, 5))) {
            return "FizzBuzz";
        }
        if (number % 3 == 0 || containsDigit(number, 3)) {
            return "Fizz";
        }
        if (number % 5 == 0 || containsDigit(number, 5)) {
            return "Buzz";
        }
        return String.valueOf(number);
    }

    private boolean containsDigit(int number, int digit) {
        return String.valueOf(number).contains(String.valueOf(digit));
    }
}