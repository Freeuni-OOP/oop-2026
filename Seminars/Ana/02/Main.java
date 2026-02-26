public class Main {
    public static void main(String[] args) {
        try {
            RomanNumeral r1 = new RomanNumeral("CXXIV");
            System.out.println(r1.toInt());
            RomanNumeral r2 = new RomanNumeral("MMMDXCVII");
            System.out.println(r2.toInt());
            RomanNumeral r3 = new RomanNumeral(1234);
            System.out.println(r3.toString());
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }
}