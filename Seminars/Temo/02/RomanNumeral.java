public class RomanNumeral {

    private Integer arabicNumber;
    private String romanNumber;
    private static final String REGEX_PATTERN = "^M{0,3}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";


    private final int[] arabicNumbers = {1, 4, 5, 9, 10, 40, 50, 90, 100, 400, 500, 900, 1000};
    private final String[] romanNumbers = {"I", "IV", "V", "IX", "X", "XL", "L", "XC", "C", "CD","D", "CM", "M"};

    RomanNumeral(int arabicNumber) {
        if (arabicNumber < 1 || arabicNumber > 3999) {
          throw new IllegalArgumentException("Arabic number must be between 1 and 3999");
        }
        this.arabicNumber = arabicNumber;
    }

    RomanNumeral(String romanNumber) {
        if (!romanNumber.matches(REGEX_PATTERN)) {
          throw new IllegalArgumentException("Invalid Roman numeral");
        }
        this.romanNumber = romanNumber;
    }


    public String toString() {
      StringBuilder sb = new StringBuilder();

      if (this.romanNumber != null) {
        return this.romanNumber;
      }

      int number = this.arabicNumber;

      for (int i = arabicNumbers.length - 1; i >= 0; i--) {
        while (number >= arabicNumbers[i]) {
          sb.append(romanNumbers[i]);
          number -= arabicNumbers[i];
        }
      }

      String roman = sb.toString();
      this.romanNumber = roman;
      return roman;
    }

    private int toArabic(char roman) {
        switch (roman) {
          case 'I': return 1;
          case 'V': return 5;
          case 'X': return 10;
          case 'L': return 50;
          case 'C': return 100;
          case 'D': return 500;
          case 'M': return 1000;
        }
        throw new IllegalArgumentException();
    }

    int toInt() {
        if (this.arabicNumber != null) {
          return this.arabicNumber;
        }
        int arabicNumber = 0;
        int previous = 0;
        for (int i = this.romanNumber.length() - 1; i >= 0; i--) {
            int current = toArabic(this.romanNumber.charAt(i));
            if (current >= previous) {
              arabicNumber += current;
            } else {
              arabicNumber -= current;
            }
            previous = current;
        }
        this.arabicNumber = arabicNumber;
        return arabicNumber;
    }

}
