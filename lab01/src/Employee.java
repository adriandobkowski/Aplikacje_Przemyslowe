import java.util.Objects;
public class Employee {
    private String name;
    private String surname;
    private String email;
    private String companyName;
    private Status companyPosition;
    private Double salary;

    public Employee(String name, String surname, String email,
                    String companyName, Status companyPosition, Double salary) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.companyName = companyName;
        this.companyPosition = companyPosition;
        this.salary = salary;
    }
    public String getSurname() {
        return this.surname;
    }
    public String getCompanyName() {
        return this.companyName;
    }
    public Status getCompanyPosition() {
        return this.companyPosition;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Employee employee = (Employee) obj;
        return employee.email.equals(email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + companyName + '\'' +
                ", companyPosition=" + companyPosition +
                ", salary=" + salary +
                '}';
    }
}
