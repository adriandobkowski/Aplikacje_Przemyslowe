package service;

import model.CompanyStatistics;
import model.Employee;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public List<Employee> getAllEmployees() {
        return new ArrayList<>(employees);
    }

    public List<Employee> validateSalaryConsistency() {
        return employees.stream()
                .filter(employee -> employee.getSalary() < employee.getCompanyPosition().getSalary())
                .collect(Collectors.toList());
    }

    public Map<String, CompanyStatistics> getCompanyStatistics() {
        return employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getCompanyName,
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                companyEmployees -> {
                                    long count = companyEmployees.size();
                                    double avgSalary = companyEmployees.stream()
                                            .mapToDouble(Employee::getSalary)
                                            .average()
                                            .orElse(0.0);
                                    Optional<Employee> highestPaid = companyEmployees.stream()
                                            .max(Comparator.comparingDouble(Employee::getSalary));
                                    String highestPaidFullName = highestPaid.map(Employee::getFullName).orElse("Brak");
                                    return new CompanyStatistics(count, avgSalary, highestPaidFullName);
                                })));
    }
}
