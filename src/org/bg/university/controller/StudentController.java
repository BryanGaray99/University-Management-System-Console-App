package org.bg.university.controller;

import java.util.Scanner;
import java.util.List;

import org.bg.university.model.Student;
import org.bg.university.model.University;
import org.bg.university.validation.StudentInputValidations;

/**
 * StudentController is a class that contains methods to create, select, and change the status of a student.
 * @author Bryan Garay
 */
public class StudentController {
    // Public Methods
    /**
     * Creates a new student and adds it to the university.
     * @param university The university to add the student to.
     */
    public static void createStudent(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create a new student:");
        System.out.print("Student name: ");
        String name = scanner.nextLine();
        // Validation
        String nameError = StudentInputValidations.validateName(name);
        if (nameError != null) {
            System.out.println(nameError);
            return;
        }

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        // Validation
        String ageError = StudentInputValidations.validateAge(age);
        if (ageError != null) {
            System.out.println(ageError);
            return;
        }

        Student newStudent = new Student(name, age);
        university.addStudent(newStudent);

        System.out.println("The student has been created.");
    }

    /**
     * Selects a student from the university.
     * @param university The university to select the student from.
     * @return The selected student, or null if no students are available.
     */
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

    /**
     * Changes the status of a student.
     * @param university The university to select the student from.
     */
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

    /**
     * Finds a student by ID.
     * @param university The university to select the student from.
     * @param studentId The ID of the student to find.
     * @return The student object, or null if the student is not found.
     */
    public static Student findStudentById(University university, int studentId) {
        for (Student student : university.getStudents()) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }
        return null;
    }

    // Private Methods
    /**
     * This method prints the list of active students.
     * @param students The list of students to print.
     */
    private static void ListActiveStudents(List<Student> students) {
        System.out.println("Active students:");
        for (int i = 0; i < students.size(); i++) {
            if (students.get(i).isActive()) {
                System.out.println((i + 1) + ". " + students.get(i).getName());
            }
        }
    }

    /**
     * This method gets the student choice from the user.
     * @param listSize The size of the list to select from.
     * @return The selected student, or -1 if the selection is invalid.
     */
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

    /**
     * This method toggles the status of a student.
     * @param student The student to toggle the status of.
     */
    private static void toggleStudentStatus(Student student) {
        boolean isActive = student.isActive();
        student.setActive(!isActive);
    }
}
