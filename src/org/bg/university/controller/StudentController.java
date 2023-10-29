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
        scanner.nextLine();

        Student newStudent = new Student(name, age);
        university.addStudent(newStudent);

        System.out.println("The student has been created.");
    }

    public static Student selectStudent(University university) {
        Scanner scanner = new Scanner(System.in);

        // List of all students
        List<Student> students = university.getStudents();

        if (students.isEmpty()) {
            System.out.println("There are no students available.");
            return null;
        }

        // List of active students
        System.out.println("Available students:");
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).isActive()) {
                System.out.println((i + 1) + ". " + students.get(i).getName());
            }
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

    public static Student findStudentById(University university, int studentId) {
        for (Student student : university.getStudents()) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    public static String findStudentNameById(University university, int studentId) {
        for (Student student : university.getStudents()) {
            if (student.getStudentId() == studentId) {
                return student.getName();
            }
        }
        return null;
    }

    public static void changeStatusStudentById(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Student ID to change status: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        Student student = findStudentById(university, studentId);

        if (student != null) {
            boolean isActive = student.isActive();
            student.setActive(!isActive);
            System.out.println("Student " + student.getName() + " with ID '" + studentId + "' has been set as " + (isActive ? "Inactive." : "Active."));
        } else {
            System.out.println("Student not found.");
        }
    }

}
