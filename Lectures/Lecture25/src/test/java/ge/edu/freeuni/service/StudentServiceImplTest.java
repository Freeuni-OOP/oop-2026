package ge.edu.freeuni.service;

import ge.edu.freeuni.dto.StudentRequest;
import ge.edu.freeuni.dto.StudentResponse;
import ge.edu.freeuni.exception.StudentNotFoundException;
import ge.edu.freeuni.model.Student;
import ge.edu.freeuni.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static ge.edu.freeuni.model.Student.Major.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;

/**
 * UNIT TESTS for StudentServiceImpl
 * <p>
 * Key concepts shown:
 *  - @ExtendWith(MockitoExtension.class)  — no Spring context loaded (fast!)
 *  - @Mock                                — creates a mock of StudentRepository
 *  - @InjectMocks                         — injects mocks into StudentServiceImpl
 *  - given / when / then (BDD style)      — readable test structure
 *  - AssertJ (assertThat)                 — fluent assertions
 *  - @Nested                              — groups related tests
 */
@ExtendWith(MockitoExtension.class)
@DisplayName("StudentService Unit Tests")
class StudentServiceImplTest {

    // ── collaborator (mocked — no real DB) ──────────────────────────────
    @Mock
    private StudentRepository studentRepository;

    // ── class under test (mocks injected via constructor) ───────────────
    @InjectMocks
    private StudentServiceImpl studentService;

    // ── shared test data ─────────────────────────────────────────────────
    private Student alice;
    private Student bob;
    private StudentRequest validRequest;

    @BeforeEach
    void setUp() {
        alice = new Student(1L, "Alice Johnson", "alice@freeuni.edu.ge", 20, COMPUTER_SCIENCE);
        bob   = new Student(2L, "Bob Smith",     "bob@freeuni.edu.ge",   22, MATHEMATICS);

        validRequest = new StudentRequest("Alice Johnson", "alice@freeuni.edu.ge", 20, COMPUTER_SCIENCE);
    }

    // ════════════════════════════════════════════════════════════════════
    //  getAllStudents()
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("getAllStudents()")
    class GetAllStudents {

        @Test
        @DisplayName("returns all students as DTOs")
        void returnsAllStudents() {
            // given — repository will return two students
            given(studentRepository.findAll()).willReturn(List.of(alice, bob));

            // when
            List<StudentResponse> result = studentService.getAllStudents();

            // then
            assertThat(result).hasSize(2);
            assertThat(result.get(0).getName()).isEqualTo("Alice Johnson");
            assertThat(result.get(1).getName()).isEqualTo("Bob Smith");
            then(studentRepository).should(times(1)).findAll();
        }

        @Test
        @DisplayName("returns empty list when no students exist")
        void returnsEmptyList() {
            given(studentRepository.findAll()).willReturn(List.of());

            List<StudentResponse> result = studentService.getAllStudents();

            assertThat(result).isEmpty();
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  getStudentById()
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("getStudentById()")
    class GetStudentById {

        @Test
        @DisplayName("returns student DTO when id exists")
        void returnsStudentWhenFound() {
            given(studentRepository.findById(1L)).willReturn(Optional.of(alice));

            StudentResponse result = studentService.getStudentById(1L);

            assertThat(result.getId()).isEqualTo(1L);
            assertThat(result.getName()).isEqualTo("Alice Johnson");
            assertThat(result.getEmail()).isEqualTo("alice@freeuni.edu.ge");
            assertThat(result.getMajor()).isEqualTo(COMPUTER_SCIENCE);
        }

        @Test
        @DisplayName("throws StudentNotFoundException when id does not exist")
        void throwsWhenNotFound() {
            given(studentRepository.findById(99L)).willReturn(Optional.empty());

            // assertThatThrownBy — verifies an exception is thrown
            assertThatThrownBy(() -> studentService.getStudentById(99L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("99");
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  createStudent()
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("createStudent()")
    class CreateStudent {

        @Test
        @DisplayName("saves and returns the new student as DTO")
        void savesAndReturnsStudent() {
            // given — repository.save() returns alice (with generated id=1)
            given(studentRepository.save(any(Student.class))).willReturn(alice);

            StudentResponse result = studentService.createStudent(validRequest);

            assertThat(result.getId()).isEqualTo(1L);
            assertThat(result.getName()).isEqualTo("Alice Johnson");
            assertThat(result.getEmail()).isEqualTo("alice@freeuni.edu.ge");

            // verify save was called exactly once
            then(studentRepository).should(times(1)).save(any(Student.class));
        }

        @Test
        @DisplayName("passes correct data to the repository")
        void passesCorrectDataToRepository() {
            given(studentRepository.save(any(Student.class))).willReturn(alice);

            studentService.createStudent(validRequest);

            // capture the argument passed to save()
            then(studentRepository).should().save(argThat(student ->
                student.getName().equals("Alice Johnson") &&
                student.getEmail().equals("alice@freeuni.edu.ge") &&
                student.getAge() == 20 &&
                student.getMajor() == COMPUTER_SCIENCE
            ));
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  updateStudent()
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("updateStudent()")
    class UpdateStudent {

        @Test
        @DisplayName("updates and returns the student")
        void updatesStudent() {
            StudentRequest updateRequest = new StudentRequest("Alice Updated", "alice-new@freeuni.edu.ge", 21, MATHEMATICS);
            Student updated = new Student(1L, "Alice Updated", "alice-new@freeuni.edu.ge", 21, MATHEMATICS);

            given(studentRepository.findById(1L)).willReturn(Optional.of(alice));
            given(studentRepository.save(any(Student.class))).willReturn(updated);

            StudentResponse result = studentService.updateStudent(1L, updateRequest);

            assertThat(result.getName()).isEqualTo("Alice Updated");
            assertThat(result.getMajor()).isEqualTo(MATHEMATICS);
        }

        @Test
        @DisplayName("throws StudentNotFoundException when student does not exist")
        void throwsWhenNotFound() {
            given(studentRepository.findById(99L)).willReturn(Optional.empty());

            assertThatThrownBy(() -> studentService.updateStudent(99L, validRequest))
                .isInstanceOf(StudentNotFoundException.class);

            // save should never be called if student doesn't exist
            then(studentRepository).should(never()).save(any());
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  deleteStudent()
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("deleteStudent()")
    class DeleteStudent {

        @Test
        @DisplayName("deletes the student when id exists")
        void deletesStudent() {
            given(studentRepository.existsById(1L)).willReturn(true);
            willDoNothing().given(studentRepository).deleteById(1L);

            assertThatNoException().isThrownBy(() -> studentService.deleteStudent(1L));

            then(studentRepository).should(times(1)).deleteById(1L);
        }

        @Test
        @DisplayName("throws StudentNotFoundException when id does not exist")
        void throwsWhenNotFound() {
            given(studentRepository.existsById(99L)).willReturn(false);

            assertThatThrownBy(() -> studentService.deleteStudent(99L))
                .isInstanceOf(StudentNotFoundException.class)
                .hasMessageContaining("99");

            then(studentRepository).should(never()).deleteById(any());
        }
    }

    // ════════════════════════════════════════════════════════════════════
    //  getStudentsByMajor()
    // ════════════════════════════════════════════════════════════════════
    @Nested
    @DisplayName("getStudentsByMajor()")
    class GetStudentsByMajor {

        @Test
        @DisplayName("returns only students with the requested major")
        void returnsStudentsByMajor() {
            given(studentRepository.findByMajor(COMPUTER_SCIENCE)).willReturn(List.of(alice));

            List<StudentResponse> result = studentService.getStudentsByMajor(COMPUTER_SCIENCE);

            assertThat(result).hasSize(1);
            assertThat(result.get(0).getMajor()).isEqualTo(COMPUTER_SCIENCE);
        }

        @Test
        @DisplayName("returns empty list when no students match the major")
        void returnsEmptyWhenNoMatch() {
            given(studentRepository.findByMajor(PHYSICS)).willReturn(List.of());

            List<StudentResponse> result = studentService.getStudentsByMajor(PHYSICS);

            assertThat(result).isEmpty();
        }
    }
}

