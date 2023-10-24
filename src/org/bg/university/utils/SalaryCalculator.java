package org.bg.university.utils;

public class SalaryCalculator {
    public static double calculateFullTimeTeacherSalary(double baseSalary, int experienceYears) {
        return baseSalary * (1 + experienceYears * 0.1);
    }

    public static double calculatePartTimeTeacherSalary(double baseSalary, int activeHoursPerWeek) {
        return baseSalary * activeHoursPerWeek;
    }
}
