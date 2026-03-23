import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SquareTest {

    @Test
    public void testArea() {
        Square square = new Square(100);
        assertEquals(square.getWidth() * square.getWidth(), square.getArea());
    }

    @ParameterizedTest
    @CsvSource({
            "1, 1",
            "2, 4",
            "5, 25",
            "10, 100",
            "100, 10000"
    })
    public void testAreaMany(int width, int expectedArea) {
        Square square = new Square(width);
        assertEquals(expectedArea, square.getArea());
    }
}
