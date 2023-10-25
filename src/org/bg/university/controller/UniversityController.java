package org.bg.university.controller;

import java.util.List;
import java.util.ArrayList;
import java.util.Scanner;

import org.bg.university.model.Class;
import org.bg.university.model.Student;
import org.bg.university.model.University;

public class UniversityController {
    public static void createAndAddStudentToClass(University university) {
        Scanner scanner = new Scanner(System.in);

        // List available students
        List<Student> students = university.getStudents();
        System.out.println("Available students:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getName());
        }

        System.out.print("Select a student to add to a class (number): ");
        int studentChoice = scanner.nextInt();
        scanner.nextLine();  // Clear the newline character

        if (studentChoice > 0 && studentChoice <= students.size()) {
            Student selectedStudent = students.get(studentChoice - 1);

            // List available classes
            List<Class> classes = university.getClasses();
            System.out.println("Available classes:");
            for (int i = 0; i < classes.size(); i++) {
                System.out.println((i + 1) + ". " + classes.get(i).getName());
            }

            System.out.print("Select a class to add the student (number): ");
            int classChoice = scanner.nextInt();
            scanner.nextLine();  // Clear the newline character

            if (classChoice > 0 && classChoice <= classes.size()) {
                Class selectedClass = classes.get(classChoice - 1);

                // Add the student to the class
                selectedClass.addStudent(selectedStudent);
                System.out.println("The student has been added to the class.");
            } else {
                System.out.println("Invalid class selection.");
            }
        } else {
            System.out.println("Invalid student selection.");
        }
    }

    public static void listClassesForStudent(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Student ID to list classes: ");
        String studentId = scanner.nextLine();

        List<Class> classes = university.getClassesForStudent(studentId);

        if (classes.isEmpty()) {
            System.out.println("No classes found for the student.");
        } else {
            System.out.println("Classes for the student with ID '" + studentId + "':");
            for (Class classObj : classes) {
                System.out.println("Class Name: " + classObj.getName());
                System.out.println("Teacher: " + classObj.getTeacher().getName());
                System.out.println("Classroom: " + classObj.getClassroom());
                System.out.println("-------------------------------");
            }
        }
    }
}
