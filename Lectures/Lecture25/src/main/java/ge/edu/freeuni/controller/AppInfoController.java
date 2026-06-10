package ge.edu.freeuni.controller;

import ge.edu.freeuni.config.AppProperties;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * FEATURE: @ConfigurationProperties injection demo
 * Shows the custom app.* properties from application.properties.
 *
 * Try: GET /api/info
 */
@RestController
@RequestMapping("/api/info")
@RequiredArgsConstructor
public class AppInfoController {

    private final AppProperties appProperties;

    @GetMapping
    public Map<String, Object> getInfo() {
        return Map.of(
            "appName",        appProperties.getName(),
            "maxStudents",    appProperties.getMaxStudents(),
            "welcomeMessage", appProperties.getWelcomeMessage()
        );
    }
}
