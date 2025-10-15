import com.opencsv.exceptions.CsvException;
import exception.ApiException;
import model.CompanyStatistics;
import model.Employee;
import model.ImportSummary;
import service.ApiService;
import service.EmployeeService;
import service.ImportService;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        EmployeeService employeeService = new EmployeeService();
        ImportService importService = new ImportService();
        ApiService apiService = new ApiService();

        try {
            ImportSummary summary = importService.importFromCsv("employees.csv");
            summary.getEmployees().forEach(employeeService::addEmployee);
            System.out.println("Import summary: " + summary);
            if (!summary.getBadLines().isEmpty()) {
                System.out.println("Errors during import:");
                summary.getBadLines().forEach(System.out::println);
            }
        } catch (IOException | CsvException e) {
            System.err.println("Error reading CSV file: " + e.getMessage());
        }

        try {
            List<Employee> apiEmployees = apiService.fetchEmployeesFromApi();
            apiEmployees.forEach(employeeService::addEmployee);
            System.out.println("Successfully fetched and added " + apiEmployees.size() + " employees from API.");
        } catch (ApiException e) {
            System.err.println("Error fetching employees from API: " + e.getMessage());
        }

        employeeService.getAllEmployees().forEach(System.out::println);

        List<Employee> inconsistentSalaries = employeeService.validateSalaryConsistency();
        if (inconsistentSalaries.isEmpty()) {
            System.out.println("All employees have salaries consistent with their positions.");
        } else {
            System.out.println("Employees with salaries lower than base for their position:");
            inconsistentSalaries.forEach(System.out::println);
        }

        Map<String, CompanyStatistics> stats = employeeService.getCompanyStatistics();
        stats.forEach((company, statistics) -> System.out.println(company + ": " + statistics));
    }
}
