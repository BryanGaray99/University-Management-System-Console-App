package org.bg.university.model;

public abstract class Teacher extends Employee {
    public Teacher(String name, int age) {
        super(name, age);
    }

    public abstract double calculateSalary();
}
