import pair.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.IntSummaryStatistics;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

public class EmployeeCatalog {
    List<Employee> employees;

    public EmployeeCatalog(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee findEmployeeWithHighestSalary() {
        return employees.stream()
            .reduce((curr, next) -> curr.getSalary() >= next.getSalary() ? curr : next)
            .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    public Employee findEmployeeWithHighestSalary1() {
        return employees.stream()
            .max(Comparator.comparingInt(Employee::getSalary))
            .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    public int getSalaryGapInDepartment(Department department) {
        var salaries = employees.stream()
            .filter(employee -> employee.getDepartment().equals(department))
            .map(Employee::getSalary)
            .toList();

        int max = salaries.stream().reduce(Integer.MIN_VALUE, Math::max);
        int min = salaries.stream().reduce(Integer.MAX_VALUE, Math::min);
        return max - min;
    }

    public int getSalaryGapInDepartment1(Department department) {
        IntSummaryStatistics gap = employees.stream()
            .filter(employee -> employee.getDepartment().equals(department))
            .mapToInt(Employee::getSalary)
            .summaryStatistics();

        return gap.getMax() - gap.getMin();
    }

    public int getSalaryGapInDepartment2(Department department) {
        Pair<Integer, Integer> gap = employees
            .stream()
            .filter(employee -> employee.getDepartment().equals(department))
            .map(employee -> new Pair<>(employee.getSalary(), employee.getSalary()))
            .reduce(new Pair<>(Integer.MIN_VALUE, Integer.MAX_VALUE),
                (current, next) -> new Pair<>(
                    Math.max(current.getKey(), next.getKey()),
                    Math.min(current.getValue(), next.getValue())));

        return gap.getKey() - gap.getValue();
    }

    public Department findDepartmentWithHighestCumulativeSalary() {
        return Arrays.stream(Department.values())
            .reduce((department1, department2) -> {
                int dep1SumOfSalaries = employees.stream()
                    .filter(employee -> employee.getDepartment().equals(department1))
                    .map(Employee::getSalary)
                    .reduce(Integer::sum)
                    .orElse(0);

                int dep2SumOfSalaries = employees.stream()
                    .filter(employee -> employee.getDepartment().equals(department2))
                    .map(Employee::getSalary)
                    .reduce(Integer::sum)
                    .orElse(0);

                return dep1SumOfSalaries >= dep2SumOfSalaries ? department1 : department2;
            })
            .orElseThrow(() -> new NoSuchElementException("Department not found"));
    }

    public Department findDepartmentWithHighestCumulativeSalary1() {
        return employees.stream()
            .collect(Collectors.groupingBy(
                Employee::getDepartment,
                Collectors.summingInt(Employee::getSalary)
            ))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElseThrow(() -> new RuntimeException("No departments found"));
    }
}
