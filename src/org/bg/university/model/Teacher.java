package org.bg.university.model;

/**
 * @author Bryan Garay
 */
public abstract class Teacher extends Employee {
    public Teacher(String name, int age) {
        super(name, age);
    }

    public abstract double calculateSalary();
}
