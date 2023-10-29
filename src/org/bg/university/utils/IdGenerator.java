package org.bg.university.utils;

public class IdGenerator {
    private static int studentIdCounter = 1;
    private static int employeeIdCounter = 1;
    private static int classIdCounter = 1;

    public static int generateStudentId() {
        return studentIdCounter++;
    }

    public static int generateEmployeeId() {
        return employeeIdCounter++;
    }

    public static int generateClassId() {
        return classIdCounter++;
    }
}