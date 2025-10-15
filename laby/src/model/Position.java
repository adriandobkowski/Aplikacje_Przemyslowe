package model;

public enum Position {
    PREZES(5, 25000),
    WICEPREZES(4, 18000),
    MANAGER(3, 12000),
    PROGRAMISTA(2, 8000),
    STAZYSTA(1, 3000);

    private int level;
    private double salary;

    Position(int level, double salary) {
        this.level = level;
        this.salary = salary;
    }

    public int getLevel() {
        return this.level;
    }

    public double getSalary() {
        return this.salary;
    }
}
