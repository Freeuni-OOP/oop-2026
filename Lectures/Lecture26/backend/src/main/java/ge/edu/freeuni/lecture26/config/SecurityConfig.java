package ge.edu.freeuni.lecture26.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

/**
 * FEATURE: Security + CORS for React integration
 * <p>
 * What changed from Lecture 25?
 * ─────────────────────────────
 * 1. CORS enabled → React (localhost:3000) is allowed to call Spring (localhost:8082)
 *    Without this the browser blocks the request even before it reaches Spring.
 * <p>
 * 2. Session policy = STATELESS → no server-side session cookies.
 *    React sends the Authorization: Basic header on every mutating request.
 * <p>
 * 3. formLogin() removed → the UI is now React, not Thymeleaf.
 * <p>
 * 4. A /api/auth/me endpoint (see AuthController) lets React verify credentials.
 * <p>
 * Security rules:
 *   GET  /api/**     → public
 *   POST/PUT/DELETE  → requires Basic Auth
 *   /actuator/**     → public
 */
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Value("${app.security.admin-username}")
    private String adminUsername;

    @Value("${app.security.admin-password}")
    private String adminPassword;

    @Value("${app.cors.allowed-origin}")
    private String allowedOrigin;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        var admin = User.builder()
            .username(adminUsername)
            .password(encoder.encode(adminPassword))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(admin);
    }

    /**
     * CORS configuration bean.
     * <p>
     * The React dev server runs on a different origin (port 3000) than the API (port 8082).
     * Browsers enforce the Same-Origin Policy, so we must explicitly allow the React origin.
     * <p>
     * allowedMethods includes OPTIONS because browsers send a preflight OPTIONS request
     * before every cross-origin POST/PUT/DELETE.  Spring must respond 200 to that preflight
     * or the actual request will never be sent.
     */
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of(allowedOrigin));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        // Allow the browser to read the Authorization header in the response
        config.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            // Apply the CORS config above
            .cors(Customizer.withDefaults())

            // No CSRF needed for a stateless REST API
            .csrf(AbstractHttpConfigurer::disable)

            // Stateless: no server-side sessions; every request must carry its own credentials
            .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/error").permitAll()
                // Allow React to call /api/auth/me with credentials to verify login
                .requestMatchers("/api/auth/me").authenticated()
                // All GET requests are public (read-only)
                .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                // All write operations require authentication
                .anyRequest().authenticated()
            )

            // HTTP Basic Auth – React sends "Authorization: Basic base64(user:pass)"
            .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
