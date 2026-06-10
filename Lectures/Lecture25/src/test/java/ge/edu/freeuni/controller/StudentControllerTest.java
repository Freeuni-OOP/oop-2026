package ge.edu.freeuni.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import ge.edu.freeuni.config.SecurityConfig;
import ge.edu.freeuni.dto.StudentRequest;
import ge.edu.freeuni.dto.StudentResponse;
import ge.edu.freeuni.exception.StudentNotFoundException;
import ge.edu.freeuni.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static ge.edu.freeuni.model.Student.Major.*;
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.BDDMockito.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * INTEGRATION TESTS for StudentController
 *
 * Key concepts shown:
 *  - @WebMvcTest         — loads ONLY the web layer (Controller + Security + Validation)
 *                          No real DB, no service — much faster than @SpringBootTest
 *  - MockMvc             — simulates HTTP requests without starting a real server
 *  - @MockitoBean        — replaces StudentService with a Mockito mock in the context
 *  - @WithMockUser       — simulates an authenticated user for secured endpoints
 *  - jsonPath("$.field") — asserts specific JSON fields in the response body
 */
@WebMvcTest(StudentController.class)
@Import(SecurityConfig.class)
@DisplayName("StudentController Integration Tests")
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;              // simulates HTTP without a real server

    @Autowired
    private ObjectMapper objectMapper;    // serializes Java objects to JSON

    @MockitoBean
    private StudentService studentService; // mock — we control what it returns

    // ── shared test data ─────────────────────────────────────────────────
    private StudentResponse aliceResponse;
    private StudentResponse bobResponse;
    private StudentRequest  validRequest;

    @BeforeEach
    void setUp() {
        aliceResponse = new StudentResponse(1L, "Alice Johnson", "alice@freeuni.edu.ge", 20, COMPUTER_SCIENCE);
        bobResponse   = new StudentResponse(2L, "Bob Smith",     "bob@freeuni.edu.ge",   22, MATHEMATICS);
        validRequest  = new StudentRequest("Alice Johnson", "alice@freeuni.edu.ge", 20, COMPUTER_SCIENCE);
    }

    // ════════════════════════════════════════════════════════════════════
    //  GET /api/students  — public endpoint
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("GET /api/students")
    class GetAllStudents {

        @Test
        @DisplayName("returns 200 with list of students (no auth needed)")
        void returnsAllStudents() throws Exception {
            given(studentService.getAllStudents()).willReturn(List.of(aliceResponse, bobResponse));

            mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id",    is(1)))
                .andExpect(jsonPath("$[0].name",  is("Alice Johnson")))
                .andExpect(jsonPath("$[0].email", is("alice@freeuni.edu.ge")))
                .andExpect(jsonPath("$[0].major", is("COMPUTER_SCIENCE")))
                .andExpect(jsonPath("$[1].name",  is("Bob Smith")));
        }

        @Test
        @DisplayName("returns 200 with empty array when no students exist")
        void returnsEmptyList() throws Exception {
            given(studentService.getAllStudents()).willReturn(List.of());

            mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  GET /api/students/{id}
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("GET /api/students/{id}")
    class GetStudentById {

        @Test
        @DisplayName("returns 200 with student when id exists")
        void returnsStudent() throws Exception {
            given(studentService.getStudentById(1L)).willReturn(aliceResponse);

            mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",    is(1)))
                .andExpect(jsonPath("$.name",  is("Alice Johnson")))
                .andExpect(jsonPath("$.email", is("alice@freeuni.edu.ge")))
                .andExpect(jsonPath("$.age",   is(20)))
                .andExpect(jsonPath("$.major", is("COMPUTER_SCIENCE")));
        }

        @Test
        @DisplayName("returns 404 with error body when id does not exist")
        void returns404WhenNotFound() throws Exception {
            given(studentService.getStudentById(99L))
                .willThrow(new StudentNotFoundException(99L));

            mockMvc.perform(get("/api/students/99"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status",  is(404)))
                .andExpect(jsonPath("$.message", containsString("99")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  GET /api/students/major/{major}
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("GET /api/students/major/{major}")
    class GetByMajor {

        @Test
        @DisplayName("returns students filtered by major")
        void returnsByMajor() throws Exception {
            given(studentService.getStudentsByMajor(COMPUTER_SCIENCE))
                .willReturn(List.of(aliceResponse));

            mockMvc.perform(get("/api/students/major/COMPUTER_SCIENCE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].major", is("COMPUTER_SCIENCE")));
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  POST /api/students  — requires authentication
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("POST /api/students")
    class CreateStudent {

        @Test
        @DisplayName("returns 201 with created student when authenticated and data valid")
        @WithMockUser(username = "admin", roles = "USER")   // simulates logged-in user
        void createsStudent() throws Exception {
            given(studentService.createStudent(any(StudentRequest.class)))
                .willReturn(aliceResponse);

            mockMvc.perform(post("/api/students")
                    .with(csrf())                           // include CSRF token
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",    is(1)))
                .andExpect(jsonPath("$.name",  is("Alice Johnson")))
                .andExpect(jsonPath("$.email", is("alice@freeuni.edu.ge")));
        }

        @Test
        @DisplayName("returns 401 when not authenticated")
        void returns401WhenUnauthenticated() throws Exception {
            // CSRF is disabled for /api/** in SecurityConfig, so no CSRF token needed.
            // Without credentials the request is rejected with 401 Unauthorized.
            mockMvc.perform(post("/api/students")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isUnauthorized());
        }

        @Test
        @DisplayName("returns 400 when name is blank")
        @WithMockUser
        void returns400WhenNameBlank() throws Exception {
            StudentRequest badRequest = new StudentRequest("", "alice@freeuni.edu.ge", 20, COMPUTER_SCIENCE);

            mockMvc.perform(post("/api/students")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(badRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status", is(400)))
                .andExpect(jsonPath("$.message", notNullValue()));
        }

        @Test
        @DisplayName("returns 400 when email is invalid")
        @WithMockUser
        void returns400WhenEmailInvalid() throws Exception {
            StudentRequest badRequest = new StudentRequest("Alice", "not-an-email", 20, COMPUTER_SCIENCE);

            mockMvc.perform(post("/api/students")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(badRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Email")));
        }

        @Test
        @DisplayName("returns 400 when age is below minimum (16)")
        @WithMockUser
        void returns400WhenAgeTooLow() throws Exception {
            StudentRequest badRequest = new StudentRequest("Alice", "alice@freeuni.edu.ge", 10, COMPUTER_SCIENCE);

            mockMvc.perform(post("/api/students")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(badRequest)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("16")));
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  PUT /api/students/{id}  — requires authentication
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("PUT /api/students/{id}")
    class UpdateStudent {

        @Test
        @DisplayName("returns 200 with updated student when authenticated")
        @WithMockUser
        void updatesStudent() throws Exception {
            StudentResponse updated = new StudentResponse(1L, "Alice Updated", "alice@freeuni.edu.ge", 21, MATHEMATICS);
            given(studentService.updateStudent(eq(1L), any(StudentRequest.class))).willReturn(updated);

            mockMvc.perform(put("/api/students/1")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is("Alice Updated")));
        }

        @Test
        @DisplayName("returns 404 when student to update does not exist")
        @WithMockUser
        void returns404WhenNotFound() throws Exception {
            given(studentService.updateStudent(eq(99L), any(StudentRequest.class)))
                .willThrow(new StudentNotFoundException(99L));

            mockMvc.perform(put("/api/students/99")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(validRequest)))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status", is(404)));
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  DELETE /api/students/{id}  — requires authentication
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("DELETE /api/students/{id}")
    class DeleteStudent {

        @Test
        @DisplayName("returns 204 No Content when student deleted")
        @WithMockUser
        void deletesStudent() throws Exception {
            willDoNothing().given(studentService).deleteStudent(1L);

            mockMvc.perform(delete("/api/students/1").with(csrf()))
                .andExpect(status().isNoContent());

            then(studentService).should(times(1)).deleteStudent(1L);
        }

        @Test
        @DisplayName("returns 404 when student to delete does not exist")
        @WithMockUser
        void returns404WhenNotFound() throws Exception {
            willThrow(new StudentNotFoundException(99L))
                .given(studentService).deleteStudent(99L);

            mockMvc.perform(delete("/api/students/99").with(csrf()))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.message", containsString("99")));
        }

        @Test
        @DisplayName("returns 401 when not authenticated")
        void returns401WhenUnauthenticated() throws Exception {
            mockMvc.perform(delete("/api/students/1"))
                .andExpect(status().isUnauthorized());
        }
    }
}

