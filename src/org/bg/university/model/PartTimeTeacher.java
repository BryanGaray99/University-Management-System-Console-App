package org.bg.university.model;

import org.bg.university.utils.SalaryCalculator;

public class PartTimeTeacher extends Teacher {
    private double baseSalary;
    private int activeHoursPerWeek;

    public PartTimeTeacher(String name, int age, int employeeId, double baseSalary, int activeHoursPerWeek) {
        super(name, age, employeeId);
        this.baseSalary = baseSalary;
        this.activeHoursPerWeek = activeHoursPerWeek;
    }

    @Override
    public double calculateSalary() {
        return SalaryCalculator.calculatePartTimeTeacherSalary(baseSalary, activeHoursPerWeek);
    }
}