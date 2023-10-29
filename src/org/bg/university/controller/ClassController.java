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

        System.out.print("Class name: ");
        String className = scanner.nextLine();
        System.out.print("Classroom: ");
        String classroom = scanner.nextLine();

        // List available teachers
        List<Teacher> teachers = university.getTeachers();
        if (teachers.isEmpty()) {
            System.out.println("There are no teachers available.");
            return;
        }

        System.out.println("Active teachers:");
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).isActive()) {
                System.out.println((i + 1) + ". " + teachers.get(i).getName());
            }
        }

        System.out.print("Select a teacher for the class (number): ");
        int teacherChoice = scanner.nextInt();
        scanner.nextLine();

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
                Class newClass = new Class(className, classroom, selectedTeacher, schedule);

                boolean addStudents = true;

                do {
                    // List available students
                    List<Student> students = university.getStudents();

                    if (students.isEmpty()) {
                        System.out.println("There are no students available.");
                        return;
                    }

                    // Active students
                    System.out.println("Active students:");
                    for (int i = 0; i < students.size(); i++) {
                        if (students.get(i).isActive()) {
                            System.out.println((i + 1) + ". " + students.get(i).getName());
                        }
                    }

                    System.out.print("Select a student to add to the class (number) or enter 0 to finish: ");
                    int studentChoice = scanner.nextInt();
                    scanner.nextLine();

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

    public static Class findClassById(University university, int classId) {
        for (Class classObj : university.getClasses()) {
            if (classObj.getClassId() == classId) {
                return classObj;
            }
        }
        return null;
    }

    public static boolean isValidTimeFormat(String time) {
        try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }
    }

    public static void printActiveClasses(University university) {
        List<Class> classes = university.getClasses();
        Scanner scanner = new Scanner(System.in);

        if (classes.isEmpty()) {
            System.out.println("There are no classes.");
            return;
        }

        System.out.println("List of active classes:");
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).isActive()) {
                System.out.println((i + 1) + ". " + classes.get(i).getName());
            }
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
        if (classObj.getTeacher().isActive()){
            System.out.println("Teacher: " + classObj.getTeacher().getName());
        } else {
            System.out.println("There isn't an active teacher for this class.");
        }
        System.out.println("List of Students:");

        List<Student> students = classObj.getStudents();
        if (students.isEmpty()) {
            System.out.println("  - There are no students in this class.");
        }

        for (Student student : students) {
            if (student.isActive()) {
                System.out.println("  - ID: " + student.getStudentId());
                System.out.println("    Name: " + student.getName());
                System.out.println("    Age: " + student.getAge());
            }
        }
        System.out.println("-------------------------------");
    }

    public static void changeStatusClassById(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID of the class to change status: ");
        int classId = Integer.parseInt(scanner.nextLine());

        Class classObj = findClassById(university, classId);

        if (classObj != null) {
            boolean isActive = classObj.isActive();
            classObj.setActive(!isActive);
            System.out.println("Class "+ classObj.getName() + " with ID '" + classId + "' has been set as " + (isActive ? "Inactive." : "Active."));
        } else {
            System.out.println("Class not found.");
        }
    }

}
