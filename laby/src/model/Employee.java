package model;

import java.util.Objects;

public class Employee {
    private String firstName;
    private String lastName;
    private String email;
    private String company;
    private Position position;
    private Double salary;

    public Employee(String firstName, String lastName, String email,
            String company, Position position, Double salary) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.company = company;
        this.position = position;
        this.salary = salary;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public Double getSalary() {
        return salary;
    }

    public String getSurname() {
        return this.lastName;
    }

    public String getCompanyName() {
        return this.company;
    }

    public Position getCompanyPosition() {
        return this.position;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
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
                "name='" + firstName + '\'' +
                ", surname='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", companyName='" + company + '\'' +
                ", companyPosition=" + position +
                ", salary=" + salary +
                '}';
    }
}
