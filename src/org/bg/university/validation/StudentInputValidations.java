package org.bg.university.validation;

/**
 * StudentInputValidations is a class that contains methods to validate students name and age
 * @author Bryan Garay
 */
public class StudentInputValidations {
    public static String validateName(String name) {
        if (name.length() < 3 || name.length() > 50) {
            return "Invalid name. The name must be between 3 and 50 characters.";
        }
        return null;
    }

    public static String validateAge(int age) {
        if (age < 15 || age > 80) {
            return "Invalid age. The age must be between 16 and 100.";
        }
        return null;
    }

}