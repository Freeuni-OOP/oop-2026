public class Main {
    public static void main(String[] args) {
        for (String arg : args) {
            try {
                RomanNumeral romanNumeral = new RomanNumeral(arg);

                System.out.println("Roman: " + romanNumeral);
                System.out.println("Arabic: " + romanNumeral.toInt());
            } catch (RuntimeException ex) {
                RomanNumeral romanNumeral = new RomanNumeral(Integer.parseInt(arg));

                System.out.println("Roman: " + romanNumeral);
                System.out.println("Arabic: " + romanNumeral.toInt());
            }
        }
    }
}
