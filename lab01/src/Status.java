public enum Status {
    Prezes(5,25000),
    Wiceprezes(4,18000),
    Manager(3,12000),
    Programista(2,8000),
    Stażysta(1,3000);


    private int level;
    private double salary;
    Status(int level, double salary) {
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
