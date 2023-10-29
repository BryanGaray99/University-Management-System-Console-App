package org.bg.university.model;

import org.bg.university.utils.IdGenerator;

/**
 * @author Bryan Garay
 */
public abstract class Employee extends User {
    private int employeeId;

    public Employee(String name, int age) {
        super(name, age);
        this.employeeId = IdGenerator.generateEmployeeId();
    }

    public int getEmployeeId() {
        return employeeId;
    }
}