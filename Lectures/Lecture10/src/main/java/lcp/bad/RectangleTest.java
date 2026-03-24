package lcp.bad;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RectangleTest {

    @Test
    public void testArea() {
        Rectangle rectangle = new Square(10);
        rectangle.setWidth(10);
        rectangle.setHeight(20);
        assertEquals(200, rectangle.getArea()); // fail
    }
}
