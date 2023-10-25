package org.bg.university.controller;

import java.util.Scanner;
import java.util.List;

import org.bg.university.model.Student;
import org.bg.university.model.University;

public class StudentController {
    public static void createStudent(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create a new student:");
        System.out.print("Student name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Clear the newline character
        System.out.print("Student ID: ");
        String studentId = scanner.nextLine();

        Student newStudent = new Student(name, age, studentId);
        university.addStudent(newStudent);

        System.out.println("The student has been created.");
    }

    public static void removeStudentById(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Student ID to remove: ");
        String studentId = scanner.nextLine();

        List<Student> students = university.getStudents();
        Student studentToRemove = null;

        for (Student student : students) {
            if (student.getStudentId().equalsIgnoreCase(studentId)) {
                studentToRemove = student;
                break;
            }
        }

        if (studentToRemove != null) {
            university.removeStudent(studentToRemove);
            System.out.println("The student with ID '" + studentId + "' has been removed.");
        } else {
            System.out.println("Student not found.");
        }
    }
}
