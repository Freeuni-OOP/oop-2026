import java.util.Map;

public class RomanNumeral {
    private static final int MIN_VALUE = 1;
    private static final int MAX_VALUE = 3999;
    private static final String REGEX = "^M{0,4}(CM|CD|D?C{0,3})(XC|XL|L?X{0,3})(IX|IV|V?I{0,3})$";
    private static final Map<Character, Integer> ROMAN_MAP = Map.of(
        'I', 1,
        'V', 5,
        'X', 10,
        'L', 50,
        'C', 100,
        'D', 500,
        'M', 1000
    );
    private static final int[] numbers = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    private static final String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};

    private int arabicNumber;
    private String romanNumber;

    /**
     * Stores arabic number
     *
     * @param arabicNumber
     * @throws IllegalArgumentException
     */
    public RomanNumeral(int arabicNumber) {
        if (arabicNumber < MIN_VALUE || arabicNumber > MAX_VALUE) {
            throw new IllegalArgumentException("Number must be between " + MIN_VALUE + " and " + MAX_VALUE);
        }
        this.arabicNumber = arabicNumber;
        this.romanNumber = null;
    }

    /**
     * Stores roman numeral
     *
     * @param romanNumber
     * @throws IllegalArgumentException
     */
    public RomanNumeral(String romanNumber) {
        if (!romanNumber.matches(REGEX)) {
            throw new IllegalArgumentException("Invalid roman number");
        }
        this.romanNumber = romanNumber;
        this.arabicNumber = 0;
    }

    public int toInt() {
        if (arabicNumber != 0)
            return arabicNumber;
        int result = 0;
        for (int i = 0; i < romanNumber.length(); i++) {
            int cur = ROMAN_MAP.get(romanNumber.charAt(i));
            if (i + 1 < romanNumber.length() && ROMAN_MAP.get(romanNumber.charAt(i + 1)) > cur) {
                result -= cur;
            } else {
                result += cur;
            }
        }
        arabicNumber = result;
        return arabicNumber;
    }

    public String toString() {
        if (romanNumber != null)
            return romanNumber;
        int temp = arabicNumber;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numbers.length; i++) {
            while (temp >= numbers[i]) {
                temp -= numbers[i];
                sb.append(symbols[i]);
            }
        }
        romanNumber = sb.toString();
        return romanNumber;
    }
}