package org.bg.university.model;

public abstract class Teacher extends Employee {
    public Teacher(String name, int age, int employeeId) {
        super(name, age, employeeId);
    }

    public abstract double calculateSalary();
}
