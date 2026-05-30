package ge.edu.freeuni.lecture26.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

/**
 * FEATURE: Auth verification endpoint (new in Lecture 26)
 * <p>
 * React calls GET /api/auth/me with Basic Auth credentials.
 * • 200 → credentials are valid, React stores them and shows the admin UI
 * • 401 → wrong credentials, React shows "Invalid username or password"
 * <p>
 * Why not POST /api/auth/login?
 * With HTTP Basic Auth there is no "login" step on the server.
 * The server validates credentials on EVERY request.
 * This endpoint simply gives React a safe way to verify them.
 */
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @GetMapping("/me")
    public Map<String, String> me(Authentication auth) {
        return Map.of(
                "username", auth.getName(),
                "role", Objects.requireNonNull(auth.getAuthorities().iterator().next().getAuthority())
        );
    }
}
