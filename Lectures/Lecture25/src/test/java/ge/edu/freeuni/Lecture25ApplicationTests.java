package ge.edu.freeuni;

import ge.edu.freeuni.config.AppProperties;
import ge.edu.freeuni.config.SecurityConfig;
import ge.edu.freeuni.controller.AppInfoController;
import ge.edu.freeuni.controller.StudentController;
import ge.edu.freeuni.dto.StudentRequest;
import ge.edu.freeuni.model.Student;
import ge.edu.freeuni.repository.StudentRepository;
import ge.edu.freeuni.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static ge.edu.freeuni.model.Student.Major.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * FULL SPRING BOOT INTEGRATION TESTS
 *
 * Key concepts shown:
 *  - @SpringBootTest        — loads the COMPLETE application context (all beans, real DB)
 *  - @AutoConfigureMockMvc  — injects MockMvc wired through the full filter chain
 *  - @ActiveProfiles("test")— uses application-test.properties → H2 instead of MySQL
 *  - @DirtiesContext        — resets the Spring context after write tests so data
 *                             does not leak between test classes
 *
 * Difference from @WebMvcTest:
 *  @WebMvcTest  — only web layer, service is mocked   → fast, isolated
 *  @SpringBootTest — full stack, real service + real DB → slower, end-to-end
 */
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DisplayName("Lecture25 Full Application Integration Tests")
class Lecture25ApplicationTests {

    @Autowired private MockMvc mockMvc;
    @Autowired private ObjectMapper objectMapper;

    // ── beans wired for direct assertions ───────────────────────────────
    @Autowired private StudentController  studentController;
    @Autowired private AppInfoController  appInfoController;
    @Autowired private StudentService     studentService;
    @Autowired private StudentRepository  studentRepository;
    @Autowired private AppProperties      appProperties;
    @Autowired private SecurityConfig     securityConfig;

    // ════════════════════════════════════════════════════════════════════
    //  Context wiring
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("Application Context")
    class ApplicationContext {

        @Test
        @DisplayName("loads successfully — all beans are wired")
        void contextLoads() {
            // If any required bean is missing the context fails before reaching here.
            assertThat(studentController).isNotNull();
            assertThat(appInfoController).isNotNull();
            assertThat(studentService).isNotNull();
            assertThat(studentRepository).isNotNull();
            assertThat(appProperties).isNotNull();
            assertThat(securityConfig).isNotNull();
        }

        @Test
        @DisplayName("AppProperties binds values from application-test.properties")
        void appPropertiesAreBound() {
            assertThat(appProperties.getName()).isEqualTo("Student Manager");
            assertThat(appProperties.getMaxStudents()).isEqualTo(100);
            assertThat(appProperties.getWelcomeMessage()).isNotBlank();
        }

        @Test
        @DisplayName("DataSeeder seeds 5 students on startup")
        void dataSeederRuns() {
            // DataSeeder runs as CommandLineRunner — verify it inserted rows into the real DB
            assertThat(studentRepository.count()).isGreaterThanOrEqualTo(5);
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  Full-stack GET endpoints (public — no auth)
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("GET endpoints (public)")
    class PublicGetEndpoints {

        @Test
        @DisplayName("GET /api/students returns 200 and seeded students")
        void getAllStudents() throws Exception {
            mockMvc.perform(get("/api/students"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(5))))
                .andExpect(jsonPath("$[0].id",    notNullValue()))
                .andExpect(jsonPath("$[0].name",  notNullValue()))
                .andExpect(jsonPath("$[0].email", notNullValue()));
        }

        @Test
        @DisplayName("GET /api/students/1 returns Alice (seeded by DataSeeder)")
        void getStudentById() throws Exception {
            mockMvc.perform(get("/api/students/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",    is(1)))
                .andExpect(jsonPath("$.name",  is("Alice Johnson")))
                .andExpect(jsonPath("$.major", is("COMPUTER_SCIENCE")));
        }

        @Test
        @DisplayName("GET /api/students/999 returns 404 with error body")
        void getStudentByIdNotFound() throws Exception {
            mockMvc.perform(get("/api/students/999"))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status",    is(404)))
                .andExpect(jsonPath("$.message",   containsString("999")))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
        }

        @Test
        @DisplayName("GET /api/students/major/COMPUTER_SCIENCE returns filtered students")
        void getStudentsByMajor() throws Exception {
            mockMvc.perform(get("/api/students/major/COMPUTER_SCIENCE"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(greaterThanOrEqualTo(1))))
                .andExpect(jsonPath("$[*].major", everyItem(is("COMPUTER_SCIENCE"))));
        }

        @Test
        @DisplayName("GET /api/info returns bound AppProperties values")
        void getAppInfo() throws Exception {
            mockMvc.perform(get("/api/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.appName",        is("Student Manager")))
                .andExpect(jsonPath("$.maxStudents",    is(100)))
                .andExpect(jsonPath("$.welcomeMessage", notNullValue()));
        }

        @Test
        @DisplayName("GET /actuator/health returns UP")
        void actuatorHealth() throws Exception {
            mockMvc.perform(get("/actuator/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status", is("UP")));
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  Full-stack write endpoints (require auth) — real DB round-trip
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("Write endpoints (authenticated, real DB)")
    @DirtiesContext  // reset context after writes so other tests see clean state
    class AuthenticatedWriteEndpoints {

        @Test
        @DisplayName("POST /api/students creates a student in the real DB")
        @WithMockUser(username = "admin")
        void createStudent() throws Exception {
            StudentRequest req = new StudentRequest(
                "Test Student", "test@freeuni.edu.ge", 22, MATHEMATICS
            );
            long countBefore = studentRepository.count();

            mockMvc.perform(post("/api/students")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id",    notNullValue()))
                .andExpect(jsonPath("$.name",  is("Test Student")))
                .andExpect(jsonPath("$.email", is("test@freeuni.edu.ge")))
                .andExpect(jsonPath("$.major", is("MATHEMATICS")));

            // verify the row was actually persisted
            assertThat(studentRepository.count()).isEqualTo(countBefore + 1);
            assertThat(studentRepository.findByEmail("test@freeuni.edu.ge")).isPresent();
        }

        @Test
        @DisplayName("PUT /api/students/{id} updates the student in the real DB")
        @WithMockUser(username = "admin")
        void updateStudent() throws Exception {
            StudentRequest req = new StudentRequest(
                "Alice Updated", "alice@freeuni.edu.ge", 21, PHYSICS
            );

            mockMvc.perform(put("/api/students/1")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name",  is("Alice Updated")))
                .andExpect(jsonPath("$.major", is("PHYSICS")));

            // verify the change is in the DB
            Student updated = studentRepository.findById(1L).orElseThrow();
            assertThat(updated.getName()).isEqualTo("Alice Updated");
            assertThat(updated.getMajor()).isEqualTo(PHYSICS);
        }

        @Test
        @DisplayName("DELETE /api/students/{id} removes the student from the real DB")
        @WithMockUser(username = "admin")
        void deleteStudent() throws Exception {
            long countBefore = studentRepository.count();

            mockMvc.perform(delete("/api/students/2").with(csrf()))
                .andExpect(status().isNoContent());

            assertThat(studentRepository.count()).isEqualTo(countBefore - 1);
            assertThat(studentRepository.findById(2L)).isEmpty();
        }

        @Test
        @DisplayName("POST /api/students returns 401 when not authenticated")
        void createStudentUnauthenticated() throws Exception {
            StudentRequest req = new StudentRequest(
                "Ghost User", "ghost@freeuni.edu.ge", 20, BUSINESS
            );

            mockMvc.perform(post("/api/students")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isUnauthorized());
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  Validation — full stack (goes through real validator + exception handler)
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("Validation (full stack)")
    class ValidationTests {

        @Test
        @DisplayName("POST with blank name returns 400 with descriptive message")
        @WithMockUser
        void blankName() throws Exception {
            StudentRequest req = new StudentRequest("", "v@freeuni.edu.ge", 20, COMPUTER_SCIENCE);

            mockMvc.perform(post("/api/students")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status",  is(400)))
                .andExpect(jsonPath("$.message", notNullValue()))
                .andExpect(jsonPath("$.timestamp", notNullValue()));
        }

        @Test
        @DisplayName("POST with invalid email returns 400")
        @WithMockUser
        void invalidEmail() throws Exception {
            StudentRequest req = new StudentRequest("Valid Name", "not-an-email", 20, COMPUTER_SCIENCE);

            mockMvc.perform(post("/api/students")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("Email")));
        }

        @Test
        @DisplayName("POST with age below 16 returns 400")
        @WithMockUser
        void ageTooLow() throws Exception {
            StudentRequest req = new StudentRequest("Valid Name", "v@freeuni.edu.ge", 10, COMPUTER_SCIENCE);

            mockMvc.perform(post("/api/students")
                    .with(csrf())
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message", containsString("16")));
        }
    }
}
