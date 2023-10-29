package org.bg.university.utils;

/**
 * This class is used to calculate the salary of a full time or part time teacher.
 * @author Bryan Garay
 */
public class SalaryCalculator {
    public static double calculateFullTimeTeacherSalary(double baseSalary, int experienceYears) {
        return baseSalary * (experienceYears * 1.1);
    }

    public static double calculatePartTimeTeacherSalary(double baseSalary, int activeHoursPerWeek) {
        return baseSalary * activeHoursPerWeek;
    }
}
