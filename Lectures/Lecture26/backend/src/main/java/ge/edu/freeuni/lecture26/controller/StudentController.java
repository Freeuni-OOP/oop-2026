package ge.edu.freeuni.lecture26.controller;

import ge.edu.freeuni.lecture26.dto.StudentRequest;
import ge.edu.freeuni.lecture26.dto.StudentResponse;
import ge.edu.freeuni.lecture26.model.Student;
import ge.edu.freeuni.lecture26.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FEATURE: REST Controller (JSON only – no Thymeleaf in Lecture 26)
 * <p>
 * Endpoints:
 * GET    /api/students           → list all       (public)
 * GET    /api/students/{id}      → get one        (public)
 * GET    /api/students/major/{m} → filter by major (public)
 * POST   /api/students           → create         (Basic Auth required)
 * PUT    /api/students/{id}      → update         (Basic Auth required)
 * DELETE /api/students/{id}      → delete         (Basic Auth required)
 * <p>
 * NEW in Lecture 26:
 * The React frontend calls these endpoints using the Fetch API / axios.
 * Write operations include an Authorization: Basic ... header built
 * from credentials stored in React state (sessionStorage in the demo).
 */
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> getAll() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponse getById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/major/{major}")
    public List<StudentResponse> getByMajor(@PathVariable Student.Major major) {
        return studentService.getByMajor(major);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentRequest req) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(req));
    }

    @PutMapping("/{id}")
    public StudentResponse update(@PathVariable Long id, @Valid @RequestBody StudentRequest req) {
        return studentService.updateStudent(id, req);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
