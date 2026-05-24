package ge.edu.freeuni.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Redirects bare root requests (localhost:8081 or localhost:8081/)
 * to the students list page.
 */
@Controller
public class RootController {

    @GetMapping("/")
    public String root() {
        return "redirect:/ui/students";
    }
}

