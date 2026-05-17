package streams;

import java.util.Arrays;
import java.util.List;

public class ReduceExample {

    public static void main(String[] args) {
        List<Integer> coefficients = Arrays.asList(1, 2, 3);

        String polynomial = buildPolynomial(coefficients);

        System.out.println(polynomial);
    }

    public static String buildPolynomial(List<Integer> coefficients) {
        int degree = coefficients.size() - 1;

        return coefficients.stream().reduce(
                "",
                (result, coefficient) -> {
                    int currentDegree = degree - coefficients.indexOf(coefficient);

                    String term;

                    if (currentDegree > 1) {
                        term = coefficient + "x^" + currentDegree;
                    } else if (currentDegree == 1) {
                        term = coefficient + "x";
                    } else {
                        term = String.valueOf(coefficient);
                    }

                    if (result.isEmpty()) {
                        return term;
                    }

                    return result + " + " + term;
                },
                (a, b) -> a + b
        );
    }
}
