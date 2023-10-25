package org.bg.university.model;

import org.bg.university.utils.SalaryCalculator;

public class FullTimeTeacher extends Teacher {
    private int experienceYears;
    private double baseSalary;

    public FullTimeTeacher(String name, int age, int employeeId, int experienceYears, double baseSalary) {
        super(name, age, employeeId);
        this.experienceYears = experienceYears;
        this.baseSalary = baseSalary;
    }

    @Override
    public double calculateSalary() {
        return SalaryCalculator.calculateFullTimeTeacherSalary(baseSalary, experienceYears);
    }
}