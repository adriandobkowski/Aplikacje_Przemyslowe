package service;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import model.Employee;
import model.Position;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

import exception.ApiException;

public class ApiService {
    public ApiService() {
    }

    private static final String API_URL = "https://jsonplaceholder.typicode.com/users";

    public List<Employee> fetchEmployeesFromApi() throws ApiException {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder().uri(URI.create(API_URL)).build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            if (response.statusCode() != 200) {
                throw new ApiException("API request failed with status code: " + response.statusCode());
            }

            Gson gson = new Gson();
            Type apiEmployeeListType = new TypeToken<List<ApiEmployee>>() {
            }.getType();
            List<ApiEmployee> apiEmployees = gson.fromJson(response.body(), apiEmployeeListType);

            return apiEmployees.stream()
                    .map(this::mapToEmployee)
                    .collect(Collectors.toList());

        } catch (IOException | InterruptedException e) {
            Thread.currentThread().interrupt();
            throw new ApiException("Error fetching data from API", e);
        } catch (Exception e) {
            throw new ApiException("Error parsing API response", e);
        }
    }

    private Employee mapToEmployee(ApiEmployee apiEmployee) {
        String[] nameParts = apiEmployee.name.split(" ", 2);
        String firstName = nameParts[0];
        String lastName = nameParts.length > 1 ? nameParts[1] : "";
        String email = apiEmployee.email;
        String companyName = (apiEmployee.company != null) ? apiEmployee.company.name : "Unknown";
        Position position = Position.PROGRAMISTA;
        double salary = position.getSalary();

        return new Employee(firstName, lastName, email, companyName, position, salary);
    }

    private static class ApiEmployee {
        String name;
        String email;
        ApiCompany company;
    }

    private static class ApiCompany {
        String name;
    }
}
