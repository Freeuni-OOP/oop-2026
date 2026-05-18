import java.util.Arrays;
import java.util.List;

public class LongestPalindrome {

    public static void main(String[] args) {
        System.out.println(findLongestPalindrome(
                Arrays.asList("madam", "racecar", "apple", "baNaNa", "Detartrated", "LeveL")));
    }

    /*
        only Latin symbols
        case-insensitive
        example: ("madam", "racecar", "apple", "baNaNa", "Detartrated", "LeveL") --> "Detartrated"
     */
    public static String findLongestPalindrome(List<String> words) {
        return words.stream()
                .filter(w -> w.equalsIgnoreCase(new StringBuilder(w).reverse().toString()))
                .reduce("", (a, b) -> a.length() >= b.length() ? a : b);
    }

}
