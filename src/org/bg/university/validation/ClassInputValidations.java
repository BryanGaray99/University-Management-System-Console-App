package org.bg.university.validation;

/**
 * ClassInputValidations is a class that contains methods to validate class input data.
 * @author Bryan Garay
 */
public class ClassInputValidations {
    public static String validateClassName(String className) {
        if (className.length() < 5 || className.length() > 50) {
            return "The class name must be between 5 and 50 characters long.";
        }
        return null;
    }

    public static String validateClassroom(String classroom) {
        if (classroom.length() < 2 || classroom.length() > 20) {
            return "The classroom must be between 2 and 20 characters long.";
        }
        return null;
    }

}
