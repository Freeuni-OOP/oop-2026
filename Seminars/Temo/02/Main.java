public class Main {
  public static void main(String[] args) {
      RomanNumeral r = new RomanNumeral("CMIV");
      int rInt = r.toInt();
      System.out.println(rInt);
      System.out.println(r.toString());

      RomanNumeral r2 = new RomanNumeral("MCMXCIV");
      System.out.println(r2.toInt());
      System.out.println(r2.toString());

      RomanNumeral r3 = new RomanNumeral(2024);
      System.out.println(r3.toInt());
      System.out.println(r3.toString());

      try {
        new RomanNumeral("MMMM");
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }

      try {
        new RomanNumeral(-1);
      } catch (Exception e) {
        System.out.println(e.getMessage());
      }
  }
}
