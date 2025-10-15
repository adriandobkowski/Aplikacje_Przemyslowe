package model;

public class CompanyStatistics {
    private final long numberOfEmployees;
    private final double averageSalary;
    private final String highestPaidEmployeeFullName;

    public CompanyStatistics(long numberOfEmployees, double averageSalary, String highestPaidEmployeeFullName) {
        this.numberOfEmployees = numberOfEmployees;
        this.averageSalary = averageSalary;
        this.highestPaidEmployeeFullName = highestPaidEmployeeFullName;
    }

    public long getNumberOfEmployees() {
        return numberOfEmployees;
    }

    public double getAverageSalary() {
        return averageSalary;
    }

    public String getHighestPaidEmployeeFullName() {
        return highestPaidEmployeeFullName;
    }

    @Override
    public String toString() {
        return "CompanyStatistics{" +
                "numberOfEmployees=" + numberOfEmployees +
                ", averageSalary=" + averageSalary +
                ", highestPaidEmployeeFullName='" + highestPaidEmployeeFullName + '\'' +
                '}';
    }
}
