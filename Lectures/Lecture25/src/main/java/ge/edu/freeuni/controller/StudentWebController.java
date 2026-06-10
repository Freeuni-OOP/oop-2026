package ge.edu.freeuni.controller;

import ge.edu.freeuni.dto.StudentRequest;
import ge.edu.freeuni.dto.StudentResponse;
import ge.edu.freeuni.model.Student;
import ge.edu.freeuni.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * FEATURE: Thymeleaf MVC Controller
 * <p>
 * Unlike @RestController (which returns JSON), @Controller returns
 * the name of a Thymeleaf template to render as HTML.
 * <p>
 * Flow:
 * 1. Method adds data to Model
 * 2. Returns template name (e.g. "students/list")
 * 3. Thymeleaf renders src/main/resources/templates/students/list.html
 */
@Controller
@RequestMapping("/ui")
@RequiredArgsConstructor
public class StudentWebController {

    private final StudentService studentService;

    // ── redirect root to student list ────────────────────────────────
    @GetMapping
    public String root() {
        return "redirect:/ui/students";
    }

    // ── LIST ─────────────────────────────────────────────────────────
    @GetMapping("/students")
    public String listStudents(
            @RequestParam(required = false) String major,
            Model model) {

        java.util.List<ge.edu.freeuni.dto.StudentResponse> students;
        if (major != null && !major.isBlank()) {
            students = studentService.getStudentsByMajor(Student.Major.valueOf(major));
            model.addAttribute("selectedMajor", major);
        } else {
            students = studentService.getAllStudents();
            model.addAttribute("selectedMajor", "");
        }
        model.addAttribute("students", students);
        long csCount = students.stream()
                .filter(s -> s.getMajor() == Student.Major.COMPUTER_SCIENCE)
                .count();
        model.addAttribute("csCount", csCount);
        model.addAttribute("majors", Student.Major.values());
        return "students/list";
    }

    // ── NEW form ──────────────────────────────────────────────────────
    @GetMapping("/students/new")
    public String newStudentForm(Model model) {
        model.addAttribute("student", new StudentRequest());
        model.addAttribute("majors", Student.Major.values());
        model.addAttribute("formTitle", "Add New Student");
        model.addAttribute("actionUrl", "/ui/students");
        return "students/form";
    }

    // ── CREATE ────────────────────────────────────────────────────────
    @PostMapping("/students")
    public String createStudent(
            @Valid @ModelAttribute("student") StudentRequest request,
            BindingResult result,
            Model model,
            RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("majors", Student.Major.values());
            model.addAttribute("formTitle", "Add New Student");
            model.addAttribute("actionUrl", "/ui/students");
            return "students/form";
        }
        studentService.createStudent(request);
        flash.addFlashAttribute("successMessage", "Student created successfully!");
        return "redirect:/ui/students";
    }

    // ── EDIT form ─────────────────────────────────────────────────────
    @GetMapping("/students/{id}/edit")
    public String editStudentForm(@PathVariable Long id, Model model) {
        StudentResponse existing = studentService.getStudentById(id);
        StudentRequest form = new StudentRequest(
                existing.getName(), existing.getEmail(),
                existing.getAge(), existing.getMajor()
        );
        model.addAttribute("student", form);
        model.addAttribute("studentId", id);
        model.addAttribute("majors", Student.Major.values());
        model.addAttribute("formTitle", "Edit Student");
        model.addAttribute("actionUrl", "/ui/students/" + id + "/edit");
        return "students/form";
    }

    // ── UPDATE ────────────────────────────────────────────────────────
    @PostMapping("/students/{id}/edit")
    public String updateStudent(
            @PathVariable Long id,
            @Valid @ModelAttribute("student") StudentRequest request,
            BindingResult result,
            Model model,
            RedirectAttributes flash) {

        if (result.hasErrors()) {
            model.addAttribute("studentId", id);
            model.addAttribute("majors", Student.Major.values());
            model.addAttribute("formTitle", "Edit Student");
            model.addAttribute("actionUrl", "/ui/students/" + id + "/edit");
            return "students/form";
        }
        studentService.updateStudent(id, request);
        flash.addFlashAttribute("successMessage", "Student updated successfully!");
        return "redirect:/ui/students";
    }

    // ── DELETE ────────────────────────────────────────────────────────
    @PostMapping("/students/{id}/delete")
    public String deleteStudent(@PathVariable Long id, RedirectAttributes flash) {
        studentService.deleteStudent(id);
        flash.addFlashAttribute("successMessage", "Student deleted successfully!");
        return "redirect:/ui/students";
    }

    // ── LOGIN page ────────────────────────────────────────────────────
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
}

