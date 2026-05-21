package junit;

import java.util.Objects;

public class Assertions {

    public static boolean assertEquals(Object expected, Object actual) {
        if (!Objects.equals(expected, actual)) {
            throw new AssertionError(
                    "Expected: " + expected
                            + ", " + "actual: " + actual);
        }
        return true;
    }
}
