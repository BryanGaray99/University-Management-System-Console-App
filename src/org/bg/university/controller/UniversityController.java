package org.bg.university.controller;

import java.util.List;
import java.util.Scanner;

import org.bg.university.model.Class;
import org.bg.university.model.Student;
import org.bg.university.model.University;

public class UniversityController {
    public static void addStudentToClass(University university) {
        Scanner scanner = new Scanner(System.in);

        // Pick a student
        Student selectedStudent = StudentController.selectStudent(university);

        while (true) {
            // List available classes
            List<Class> classes = university.getClasses();
            System.out.println("Available classes:");
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ". " + classes.get(i).getName());
            }

            System.out.print("Select a class to add the student (number) or enter 0 to exit: ");
            int classChoice = scanner.nextInt();
            scanner.nextLine();

            if (classChoice == 0) {
                break; // Exit the loop
            } else if (classChoice > 0 && classChoice <= classes.size()) {
                Class selectedClass = classes.get(classChoice - 1);

                if (selectedClass.getStudents().contains(selectedStudent)) {
                    System.out.println("The student is already in this class.");
                } else {
                    selectedClass.addStudent(selectedStudent);
                    System.out.println("The student has been added to the class.");
                }
            } else {
                System.out.println("Invalid class selection.");
            }
        }
    }

    public static void listClassesForStudent(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Student ID to list classes: ");
        int studentId = scanner.nextInt();

        List<Class> classes = university.getClassesForStudent(studentId);

        if (classes.isEmpty()) {
            System.out.println("No classes found for the student.");
        } else {
            String studentName = university.getStudentNameById(studentId);
            System.out.println("Classes for the student with ID '" + studentId + "':");
            System.out.println("Name of the student: " + studentName);
            for (Class classObj : classes) {
                System.out.println("Class Name: " + classObj.getName());
                System.out.println("Teacher: " + classObj.getTeacher().getName());
                System.out.println("Classroom: " + classObj.getClassroom());
                System.out.println("Schedule: " + classObj.getWeeklySchedule());
                System.out.println("-------------------------------");
            }
        }
    }
}
