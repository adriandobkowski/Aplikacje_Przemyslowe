package model;

import java.util.List;

public class ImportSummary {
    private final List<Employee> employees;
    private final List<String> badLines;

    public ImportSummary(List<Employee> employees, List<String> badLines) {
        this.employees = employees;
        this.badLines = badLines;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public List<String> getBadLines() {
        return badLines;
    }

    @Override
    public String toString() {
        return "ImportSummary{" +
                "imported=" + employees.size() +
                ", errors=" + badLines.size() +
                '}';
    }
}
