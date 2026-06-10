package ge.edu.freeuni.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * FEATURE: @ConfigurationProperties
 * Binds properties prefixed with "app" from application.properties
 * into a strongly-typed Java bean.
 * <p>
 * Properties used:
 *   app.name, app.max-students, app.welcome-message
 *   app.security.admin-username, app.security.admin-password
 */
@Component
@ConfigurationProperties(prefix = "app")
@Getter
@Setter
public class AppProperties {

    private String name;
    private int maxStudents;
    private String welcomeMessage;

    /** Nested group for app.security.* properties */
    private Security security = new Security();

    @Getter
    @Setter
    public static class Security {
        private String adminUsername;
        private String adminPassword;
    }
}

