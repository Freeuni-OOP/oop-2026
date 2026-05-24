package ge.edu.freeuni.config;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * FEATURE: Spring Security
 *
 * Credentials come from environment variables (via AppProperties), NOT hardcoded.
 *   ADMIN_USERNAME / ADMIN_PASSWORD  → set in the environment before starting the app.
 *   Fallback values in application.properties are for local dev only.
 *
 * Passwords are hashed with BCrypt — never stored in plaintext.
 *
 * Rules:
 *  - GET  /api/**  -> public
 *  - GET  /ui/**   -> public (read pages)
 *  - POST /ui/**   -> requires form login
 *  - POST/PUT/DELETE /api/** -> requires Basic Auth
 *  - /h2-console, /actuator/** -> open
 */
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final AppProperties appProperties;

    /**
     * BCrypt password encoder — industry standard, applies a random salt automatically.
     * Never store or compare plain-text passwords.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * In-memory user store.
     * Username and password are read from environment variables at startup.
     * The raw password is hashed with BCrypt before being stored in memory.
     */
    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder) {
        var admin = User.builder()
            .username(appProperties.getSecurity().getAdminUsername())
            .password(encoder.encode(appProperties.getSecurity().getAdminPassword()))
            .roles("ADMIN")
            .build();
        return new InMemoryUserDetailsManager(admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").permitAll()
                .requestMatchers("/actuator/**").permitAll()
                .requestMatchers("/css/**", "/js/**", "/images/**").permitAll()
                .requestMatchers("/error").permitAll()
                .requestMatchers("/ui/login").permitAll()
                .requestMatchers(HttpMethod.GET, "/api/**").permitAll()
                .requestMatchers(HttpMethod.GET, "/ui/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/ui/login")
                .defaultSuccessUrl("/ui/students", true)
                .permitAll()
            )
            .logout(logout -> logout
                .logoutUrl("/ui/logout")
                .logoutSuccessUrl("/ui/students")
                .permitAll()
            )
            .httpBasic(Customizer.withDefaults())
            .csrf(csrf -> csrf.ignoringRequestMatchers("/h2-console/**", "/api/**"))
            .headers(h -> h.frameOptions(HeadersConfigurer.FrameOptionsConfig::sameOrigin));
        return http.build();
    }
}


