package org.bg.university.model;

public abstract class Employee extends User {
    private int employeeId;

    public Employee(String name, int age, int employeeId) {
        super(name, age);
        this.employeeId = employeeId;
    }

    public int getEmployeeId() {
        return employeeId;
    }
}