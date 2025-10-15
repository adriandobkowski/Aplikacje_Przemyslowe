package service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import model.Employee;
import model.ImportSummary;
import model.Position;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import exception.InvalidDataException;

public class ImportService {
    public ImportService() {
    }

    public ImportSummary importFromCsv(String path) throws IOException, CsvException {
        try (CSVReader reader = new CSVReader(new BufferedReader(new FileReader(path)))) {
            List<String[]> allRows = reader.readAll();
            List<Employee> employees = new ArrayList<>();
            List<String> badLines = new ArrayList<>();
            for (int i = 1; i < allRows.size(); i++) {
                String[] row = allRows.get(i);
                try {
                    if (row.length < 6) {
                        throw new InvalidDataException("Row has fewer columns than expected.");
                    }
                    String firstName = row[0];
                    String lastName = row[1];
                    String email = row[2];
                    String company = row[3];
                    String pos = row[4];
                    double salary = Double.parseDouble(row[5]);

                    if (salary <= 0) {
                        throw new InvalidDataException("Salary must be positive.");
                    }

                    Position position = Position.valueOf(pos.toUpperCase());

                    Employee importedEmployee = new Employee(firstName, lastName, email, company, position, salary);
                    employees.add(importedEmployee);
                } catch (IllegalArgumentException e) {
                    badLines.add("Row nr: " + (i + 1) + " - Invalid position specified.");
                } catch (InvalidDataException e) {
                    badLines.add("Row nr: " + (i + 1) + " - " + e.getMessage());
                }
            }
            return new ImportSummary(employees, badLines);
        }
    }
}
