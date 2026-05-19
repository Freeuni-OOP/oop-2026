import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EmployeeCatalogTest {
    private static EmployeeCatalog catalog;

    @BeforeAll
    public static void setUp() {
        List<Employee> employees = List.of(
            new Employee("Alice", Department.IT, 9500),
            new Employee("Bob", Department.IT, 7200),
            new Employee("Carol", Department.IT, 8100),
            new Employee("Dave", Department.MARKETING, 6000),
            new Employee("Eve", Department.MARKETING, 8500),
            new Employee("Frank", Department.HR, 5500),
            new Employee("Grace", Department.HR, 6200),
            new Employee("Heidi", Department.HR, 11000),
            new Employee("Ivan", Department.HR, 9800),
            new Employee("Judy", Department.SALES, 7700),
            new Employee("Karl", Department.SALES, 8300),
            new Employee("Laura", Department.SALES, 6900)
        );
        catalog = new EmployeeCatalog(employees);
    }

    @Test
    public void testFindEmployeeWithHighestSalary() {
        assertEquals("Heidi", catalog.findEmployeeWithHighestSalary().getName());
    }
}
