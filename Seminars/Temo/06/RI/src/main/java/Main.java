public class Main {

  public static void main(String[] args) {
      {
          RationalNumber r1 = new RationalNumber(1, 2);
          System.out.println("R1: " + r1);
          RationalNumber r2 = new RationalNumber(3, 4);
          System.out.println("R2: " + r2);
          r1.add(r2);
          System.out.println("Result of addition: " + r1);
      }

      System.out.println("================================");
      {
          RationalNumber r0 = new RationalNumber(2, -4);
          System.out.println("R0: " + r0);
          System.out.println("---------------------------------");
          RationalNumber r1 = new RationalNumber(2, 4);
          System.out.println("R1: " + r1);
          RationalNumber r2 = new RationalNumber(3, 3);
          System.out.println("R2: " + r2);
          r1.subtract(r2);
          System.out.println("Result of subtraction: " + r1);
      }

      System.out.println("================================");
      {
          RationalNumber r1 = new RationalNumber(0, 8);
          System.out.println("R1: " + r1);
      }

      System.out.println("================================");
      try {
          RationalNumber r1 = new RationalNumber(1, 0);
      } catch (IllegalArgumentException e) {
          System.out.println("Caught exception for zero denominator: " + e.getMessage());
      }

      System.out.println("================================");
      {
          RationalNumber r1 = new RationalNumber(1, 2);
          RationalNumber r2 = new RationalNumber(2, 4);
          System.out.println("R1: " + r1);
          System.out.println("R2: " + r2);
          r1.multiply(r2);
          System.out.println("Result of multiplication: " + r1);
      }

      System.out.println("================================");
      {
          RationalNumber r1 = new RationalNumber(1, 2);
          RationalNumber r2 = new RationalNumber(2, 4);
          System.out.println("R1: " + r1);
          System.out.println("R2: " + r2);
          r1.divide(r2);
          System.out.println("Result of division: " + r1);
      }
  }

}
