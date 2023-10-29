package org.bg.university.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bg.university.model.Class;
import org.bg.university.model.Student;
import org.bg.university.model.University;

public class UniversityController {
    // Public methods
    public static void addStudentToClass(University university) {
        Scanner scanner = new Scanner(System.in);
        Student selectedStudent = StudentController.selectStudent(university);
        if (selectedStudent == null) {
            return;
        }

        List<Class> activeClasses = ClassController.getActiveClasses(university);
        if (activeClasses.isEmpty()) {
            System.out.println("There are no classes available.");
            return;
        }

        int classChoice;
        do {
            ClassController.printListActiveClasses(activeClasses);

            System.out.print("Select a class to add the student (number) or enter 0 to exit: ");
            classChoice = scanner.nextInt();
            scanner.nextLine();

            if (classChoice > 0 && classChoice <= activeClasses.size()) {
                Class selectedClass = activeClasses.get(classChoice - 1);
                ClassController.addStudentToClass(selectedClass, selectedStudent);
            } else {
                System.out.println("Invalid selection. Please enter a valid class ID.");
            }
        } while (classChoice != 0);
    }

    public static void listClassesForStudent(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Student ID to list classes: ");
        int studentId = scanner.nextInt();

        Student student = StudentController.findStudentById(university, studentId);

        if (student == null) {
            System.out.println("Student not found.");
        } else if (student.isActive()) {
            List<Class> classes = getActiveClassesForStudent(university, studentId);

            if (classes.isEmpty()) {
                System.out.println("No active classes found for the student.");
            } else {
                printClassesForStudent(studentId, student.getName(), classes);
            }
        } else {
            System.out.println("Student is inactive.");
        }
    }

    // Private methods
    private static List<Class> getActiveClassesForStudent(University university, int studentId) {
        List<Class> classesForStudent = new ArrayList<>();

        for (Class classObj : university.getClasses()) {
            List<Student> studentsInClass = classObj.getStudents();
            for (Student student : studentsInClass) {
                if (student.getStudentId() == studentId && classObj.isActive()) {
                    classesForStudent.add(classObj);
                    break;
                }
            }
        }
        return classesForStudent;
    }

    private static void printClassesForStudent(int studentId, String studentName, List<Class> classes) {
        System.out.println("Classes for the active student with ID '" + studentId + "':");
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
