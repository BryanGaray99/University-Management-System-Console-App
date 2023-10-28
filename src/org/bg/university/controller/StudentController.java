package org.bg.university.controller;

import java.util.Scanner;
import java.util.List;

import org.bg.university.model.Class;
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
        int studentId = scanner.nextInt();

        Student newStudent = new Student(name, age, studentId);
        university.addStudent(newStudent);

        System.out.println("The student has been created.");
    }

    public static void removeStudentById(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Student ID to remove: ");
        int studentId = scanner.nextInt();

        List<Student> students = university.getStudents();
        Student studentToRemove = null;

        for (Student student : students) {
            if (student.getStudentId() == studentId) {
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

    public static Student selectStudent(University university) {
        Scanner scanner = new Scanner(System.in);

        // List available students
        List<Student> students = university.getStudents();
        System.out.println("Available students:");
        for (int i = 0; i < students.size(); i++) {
            System.out.println((i + 1) + ". " + students.get(i).getName());
        }

        System.out.print("Select a student to add to a class (number): ");
        int studentChoice = scanner.nextInt();
        scanner.nextLine();

        if (studentChoice <= 0 || studentChoice > students.size()) {
            System.out.println("Invalid student selection.");
            return null;
        }

        return students.get(studentChoice - 1);
    }
}
