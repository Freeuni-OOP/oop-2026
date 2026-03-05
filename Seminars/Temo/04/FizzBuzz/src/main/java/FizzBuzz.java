public class FizzBuzz {

  public static String evaluate(int i) {
    String number = String.valueOf(i);
    if ((i % 3 == 0 && i % 5 == 0) || (number.contains("3") && number.contains("5"))) {
      return "FizzBuzz";
    }
    if (i % 3 == 0 || number.contains("3")) {
      return "Fizz";
    }
    if (i % 5 == 0 || number.contains("5")) {
      return "Buzz";
    }
    return number;
  }

}
