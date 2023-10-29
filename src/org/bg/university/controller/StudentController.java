package org.bg.university.controller;

import java.util.Scanner;
import java.util.List;

import org.bg.university.model.Student;
import org.bg.university.model.University;

public class StudentController {
    // Public Methods
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
        List<Student> students = university.getStudents();

        if (students.isEmpty()) {
            System.out.println("There are no students available.");
            return null;
        }

        ListActiveStudents(students);

        int studentChoice = getStudentChoiceFromUser(students.size());
        if (studentChoice == -1) {
            System.out.println("Invalid student selection.");
            return null;
        }
        return students.get(studentChoice);
    }

    public static void changeStatusStudentById(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Student ID to change status: ");
        int studentId = Integer.parseInt(scanner.nextLine());

        Student student = findStudentById(university, studentId);

        if (student != null) {
            toggleStudentStatus(student);
            System.out.println("Student " + student.getName() + " with ID '" + studentId + "' has been set as " + (student.isActive() ? "Active." : "Inactive."));
        } else {
            System.out.println("Student not found.");
        }
    }

    public static Student findStudentById(University university, int studentId) {
        for (Student student : university.getStudents()) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    // Private Methods
    private static void ListActiveStudents(List<Student> students) {
        System.out.println("Active students:");
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).isActive()) {
                System.out.println((i + 1) + ". " + students.get(i).getName());
            }
        }
    }

    private static int getStudentChoiceFromUser(int listSize) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Select a student to add to a class (number), 0 to exit: ");
        int studentChoice = scanner.nextInt();
        scanner.nextLine();

        if (studentChoice <= 0 || studentChoice > listSize) {
            return -1;
        }
        return studentChoice - 1;
    }

    private static void toggleStudentStatus(Student student) {
        boolean isActive = student.isActive();
        student.setActive(!isActive);
    }
}
