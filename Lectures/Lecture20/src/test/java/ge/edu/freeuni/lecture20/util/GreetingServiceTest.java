package ge.edu.freeuni.lecture20.util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GreetingServiceTest {

    private final GreetingService greetingService = new GreetingService();

    @Test
    public void testBuildGreetingUsesNameWhenProvided() {
        String greeting = greetingService.buildGreeting("Nika");

        assertEquals("Hello, Nika!", greeting);
    }

    @Test
    public void testBuildGreetingReturnsGuestForBlankName() {
        String greeting = greetingService.buildGreeting("   ");

        assertEquals("Hello, Guest!!", greeting);
    }

    @Test
    public void testBuildGreetingReturnsGuestForNullName() {
        String greeting = greetingService.buildGreeting(null);

        assertEquals("Hello, Guest!", greeting);
    }
}

