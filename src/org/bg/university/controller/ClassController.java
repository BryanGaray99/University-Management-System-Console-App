package org.bg.university.controller;

import java.time.format.DateTimeParseException;
import java.util.List;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import org.bg.university.model.Class;
import org.bg.university.model.Teacher;
import org.bg.university.model.Student;
import org.bg.university.model.WeeklySchedule;
import org.bg.university.model.University;

public class ClassController {
    public static void createAndAddClass(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create a new class:");
        System.out.print("Class ID: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Class name: ");
        String className = scanner.nextLine();
        System.out.print("Classroom: ");
        String classroom = scanner.nextLine();

        // List available teachers
        List<Teacher> teachers = university.getTeachers();
        System.out.println("Available teachers:");
        for (int i = 0; i < teachers.size(); i++) {
            System.out.println((i + 1) + ". " + teachers.get(i).getName());
        }

        System.out.print("Select a teacher for the class (number): ");
        int teacherChoice = scanner.nextInt();
        scanner.nextLine();  // Clear the newline character

        if (teacherChoice > 0 && teacherChoice <= teachers.size()) {
            Teacher selectedTeacher = teachers.get(teacherChoice - 1);

            // Create weekly schedule
            System.out.print("Days of the week (e.g., Monday, Tuesday, Wednesday): ");
            String daysOfWeekInput = scanner.nextLine();
            String daysOfWeek = daysOfWeekInput.replaceAll("\\s+", "");

            System.out.print("Start hour (HH:mm): ");
            String startHour = scanner.nextLine();

            System.out.print("End hour (HH:mm): ");
            String endHour = scanner.nextLine();

            if (isValidTimeFormat(startHour) && isValidTimeFormat(endHour)) {
                WeeklySchedule schedule = new WeeklySchedule(daysOfWeek, startHour, endHour);
                // Create a new class
                Class newClass = new Class(classId, className, classroom, selectedTeacher, schedule);

                boolean addStudents = true;


                do {
                    // List available students
                    List<Student> students = university.getStudents();
                    System.out.println("Available students:");
                    for (int i = 0; i < students.size(); i++) {
                        System.out.println((i + 1) + ". " + students.get(i).getName());
                    }

                    System.out.print("Select a student to add to the class (number) or enter 0 to finish: ");
                    int studentChoice = scanner.nextInt();
                    scanner.nextLine();  // Clear the newline character

                    if (studentChoice == 0) {
                        addStudents = false;
                    } else if (studentChoice > 0 && studentChoice <= students.size()) {
                        Student selectedStudent = students.get(studentChoice - 1);

                        // Check if the student is already in the class
                        if (newClass.getStudents().contains(selectedStudent)) {
                            System.out.println(selectedStudent.getName() + " is already in the class.");
                        } else {
                            newClass.addStudent(selectedStudent);
                            System.out.println(selectedStudent.getName() + " has been added to the class.");
                        }
                    } else {
                        System.out.println("Invalid student selection.");
                    }
                } while (addStudents);

                // Add the new class to the university
                university.addClass(newClass);
                System.out.println("The class has been created, and the students have been added.");
            } else {
                System.out.println("Invalid time format. Please use the 'HH:mm' format (e.g., 08:30).");
            }
        } else {
            System.out.println("Invalid teacher selection.");
        }
    }

    public static boolean isValidTimeFormat(String time) {
        try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static void removeClassById(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID of the class to remove: ");
        int classId = scanner.nextInt();
        scanner.nextLine();

        // Find the class by ID
        Class classToRemove = null;
        List<Class> classes = university.getClasses();

        for (Class classObj : classes) {
            if (classObj.getClassId() == classId) {
                classToRemove = classObj;
                break;
            }
        }

        if (classToRemove != null) {
            university.removeClass(classToRemove);
            System.out.println("The class with ID '" + classId + "' has been removed.");
        } else {
            System.out.println("Class not found.");
        }
    }

    public static void printAllClasses(University university) {
        List<Class> classes = university.getClasses();
        Scanner scanner = new Scanner(System.in);

        System.out.println("List of classes:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i).getName());
        }

        System.out.println("0. Exit");

        int choice;
        do {
            System.out.print("Select a class (0 to exit): ");
            choice = scanner.nextInt();
            scanner.nextLine();

            if (choice < 0 || choice > classes.size()) {
                System.out.println("Invalid selection. Please enter a valid class ID.");
            } else if (choice > 0) {
                Class selectedClass = classes.get(choice - 1);
                printClassDetails(selectedClass);
            }
        } while (choice != 0);
    }

    public static void printClassDetails(Class classObj) {
        System.out.println("Class Name: " + classObj.getName());
        System.out.println("Classroom: " + classObj.getClassroom());
        System.out.println("Weekly Schedule: " + classObj.getWeeklySchedule());
        System.out.println("Teacher: " + classObj.getTeacher().getName());
        System.out.println("List of Students:");

        List<Student> students = classObj.getStudents();
        for (Student student : students) {
            System.out.println("  - Name: " + student.getName());
            System.out.println("    ID: " + student.getStudentId());
            System.out.println("    Age: " + student.getAge());
        }
        System.out.println("-------------------------------");
    }
}
