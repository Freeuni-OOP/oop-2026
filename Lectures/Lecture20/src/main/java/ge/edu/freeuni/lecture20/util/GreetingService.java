package ge.edu.freeuni.lecture20.util;

public class GreetingService {

    public String buildGreeting(String name) {
        if (name == null || name.trim().isEmpty()) {
            return "Hello, Guest!";
        }

        String normalized = name.trim();
        return "Hello, " + normalized + "!";
    }
}

