package ge.edu.freeuni.controller;

import ge.edu.freeuni.dto.StudentRequest;
import ge.edu.freeuni.dto.StudentResponse;
import ge.edu.freeuni.*;
import ge.edu.freeuni.model.Student;
import ge.edu.freeuni.service.StudentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * FEATURE: REST Controller
 *
 * @RestController  = @Controller + @ResponseBody (auto JSON serialization)
 * @RequestMapping  sets the base path for all endpoints
 * <p>
 * Endpoints:
 *   GET    /api/students          -> list all  (public)
 *   GET    /api/students/{id}     -> get one   (public)
 *   GET    /api/students/major/{major} -> filter by major (public)
 *   POST   /api/students          -> create    (requires auth)
 *   PUT    /api/students/{id}     -> update    (requires auth)
 *   DELETE /api/students/{id}     -> delete    (requires auth)
 */
@RestController
@RequestMapping("/api/students")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @GetMapping
    public List<StudentResponse> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{id}")
    public StudentResponse getStudentById(@PathVariable Long id) {
        return studentService.getStudentById(id);
    }

    @GetMapping("/major/{major}")
    public List<StudentResponse> getByMajor(@PathVariable Student.Major major) {
        return studentService.getStudentsByMajor(major);
    }

    @PostMapping
    public ResponseEntity<StudentResponse> createStudent(@Valid @RequestBody StudentRequest request) {
        StudentResponse created = studentService.createStudent(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }

    @PutMapping("/{id}")
    public StudentResponse updateStudent(
        @PathVariable Long id,
        @Valid @RequestBody StudentRequest request
    ) {
        return studentService.updateStudent(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
        studentService.deleteStudent(id);
        return ResponseEntity.noContent().build();
    }
}
