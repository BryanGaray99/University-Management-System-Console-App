package org.bg.university.model;

import org.bg.university.utils.SalaryCalculator;

/**
 * @author Bryan Garay
 */
public class FullTimeTeacher extends Teacher {
    private int experienceYears;
    private double baseSalary;

    public FullTimeTeacher(String name, int age, int experienceYears, double baseSalary) {
        super(name, age);
        this.experienceYears = experienceYears;
        this.baseSalary = baseSalary;
    }

    @Override
    public double calculateSalary() {
        return SalaryCalculator.calculateFullTimeTeacherSalary(baseSalary, experienceYears);
    }
}