import java.util.*;
import java.util.stream.Collectors;

public class EmployeeSystem {
    private ArrayList<Employee> employees;
    public EmployeeSystem(ArrayList<Employee> employees){
        this.employees = employees;
    }
    public void addEmployee(Employee employee) {
        for (Employee e: employees) {
            if (e.equals(employee)) {
                return;
            }
        }
        employees.add(employee);
    }
    public ArrayList<Employee> getEmployees() {
        return this.employees;
    }
    public ArrayList<Employee> findEmployee(String companyName) {
        return employees.stream().filter(e -> e.getCompanyName().equals(companyName)).collect(Collectors.toCollection(ArrayList::new));
    }
    public ArrayList<Employee> presentEmployeesBySurname() {
        employees.sort(new Comparator<Employee>() {
            @Override
            public int compare(Employee employee1, Employee employee2) {
                return employee1.getSurname().compareTo(employee2.getSurname());
            }
        });
        return employees;
    }
    public Map<Status, List<Employee>> groupEmployeesByStatus() {
        return employees.stream().collect(Collectors.groupingBy(Employee::getCompanyPosition));
    }
    public int getEmployeeNumberByStatus(Status status) {
        return groupEmployeesByStatus().getOrDefault(status, Collections.emptyList()).size();
    }
    public double getAverageSalary() {
        return employees.stream()
                .mapToDouble(e -> e.getCompanyPosition().getSalary())
                .average()
                .orElse(0.0);
    }
    public Optional<Employee> findHighestPaidEmployee() {
        return employees.stream().max(new Comparator<Employee>() {
            @Override
            public int compare(Employee e1, Employee e2) {
                return Double.compare(e1.getCompanyPosition().getSalary(), e2.getCompanyPosition().getSalary());
            }
        });
    }
}
