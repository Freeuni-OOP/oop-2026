import javafx.util.Pair;

import java.util.*;

public class EmployeeCatalog {

    private final List<Employee> employees;

    public EmployeeCatalog(List<Employee> employees) {
        this.employees = employees;
    }

    public Employee findEmployeeWithHighestSalary() {
        return employees.stream()
                .reduce((curr, next) -> curr.getSalary() > next.getSalary() ? curr : next)
                .orElseThrow(() -> new NoSuchElementException("Employee not found"));
    }

    public int getSalaryGapInDepartment(Department department) {
        Pair<Integer, Integer> maxMin = employees.stream()
                .filter(employee -> employee.getDepartment().equals(department))
                .map(employee -> new Pair<>(employee.getSalary(), employee.getSalary()))
                .reduce((curr, next) -> new Pair<>(Math.max(curr.getKey(), next.getKey()),
                        Math.min(curr.getValue(), next.getValue()))
                ).orElseGet(() -> new Pair<>(0, 0));

        return maxMin.getKey() - maxMin.getValue();
    }

    public Department findDepartmentWithHighestCumulativeSalary() {

        return employees.stream()
                .map(employee -> {
                    Map<Department, Integer> result = new HashMap<>();
                    result.put(employee.getDepartment(), employee.getSalary());
                    return result;
                })
                .reduce(new HashMap<>(),
                        (curr, next) -> {
                            Department department = next.keySet().iterator().next();
                            int salary = next.get(department);

                            curr.put(department, curr.getOrDefault(department, 0) + salary);
                            return curr;
                        })
                .entrySet()
                .stream()
                .reduce((curr, next) -> curr.getValue() > next.getValue() ? curr : next)
                .get()
                .getKey();
    }
}