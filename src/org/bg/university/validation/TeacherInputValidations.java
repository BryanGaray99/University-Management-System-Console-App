package org.bg.university.validation;

/**
 * TeacherInputValidations is a class that contains methods to validate teacher input data.
 * @author Bryan Garay
 */
public class TeacherInputValidations {
    public static String validateName(String name) {
        if (name.length() < 3 || name.length() > 50) {
            return "Invalid name. The name must be between 3 and 50 characters.";
        }
        return null;
    }

    public static String validateAge(int age) {
        if (age < 18 || age > 80) {
            return "Invalid age. The age must be between 18 and 80.";
        }
        return null;
    }

    public static String isBaseSalaryValid(double baseSalary) {
        if (baseSalary < 0 || baseSalary > 10000) {
            return "Invalid base salary. The base salary must be between 0 and 10000.";
        }
        return null;
    }

    public static String isExperienceYearsValid(int experienceYears) {
        if (experienceYears < 1 || experienceYears > 70) {
            return "Invalid experience years. The experience years must be between 1 and 70.";
        }
        return null;
    }

    public static String isActiveHoursPerWeekValid(int activeHoursPerWeek) {
        if (activeHoursPerWeek < 0 || activeHoursPerWeek > 40) {
            return "Invalid active hours per week. The active hours per week must be between 0 and 40.";
        }
        return null;
    }

}