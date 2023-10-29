package org.bg.university.model;

import org.bg.university.utils.SalaryCalculator;

/**
 * @author Bryan Garay
 */
public class PartTimeTeacher extends Teacher {
    private double baseSalary;
    private int activeHoursPerWeek;

    public PartTimeTeacher(String name, int age, double baseSalary, int activeHoursPerWeek) {
        super(name, age);
        this.baseSalary = baseSalary;
        this.activeHoursPerWeek = activeHoursPerWeek;
    }

    @Override
    public double calculateSalary() {
        return SalaryCalculator.calculatePartTimeTeacherSalary(baseSalary, activeHoursPerWeek);
    }
}