package org.bg.university.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.bg.university.model.Class;
import org.bg.university.model.Student;
import org.bg.university.model.University;

public class UniversityController {
    public static void addStudentToClass(University university) {
        Scanner scanner = new Scanner(System.in);

        // Pick an active student
        Student selectedStudent = StudentController.selectStudent(university);

        int classChoice;
        do {
            // List available classes
            List<Class> classes = university.getClasses();

            if (classes.isEmpty()) {
                System.out.println("There are no classes available.");
                return;
            }

            // List active classes
            System.out.println("Active classes:");
            for (int i = 0; i < classes.size(); i++) {
                if (classes.get(i).isActive()) {
                    System.out.println((i + 1) + ". " + classes.get(i).getName());
                }
            }

            System.out.print("Select a class to add the student (number) or enter 0 to exit: ");
            classChoice = scanner.nextInt();
            scanner.nextLine();

            if (classChoice > 0 && classChoice <= classes.size()) {
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
            List<Class> classes = getClassesForStudent(university, studentId);

            if (classes.isEmpty()) {
                System.out.println("No active classes found for the student.");
            } else {
                String studentName = StudentController.findStudentNameById(university, studentId);
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
        } else {
            System.out.println("Student is  inactive.");
        }
    }

    public static List<Class> getClassesForStudent(University university, int studentId) {
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
}
