package org.bg.university.controller;

import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
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
            String[] daysOfWeek = daysOfWeekInput.split(", ");
            System.out.print("Start hour: ");
            int startHour = scanner.nextInt();
            System.out.print("End hour: ");
            int endHour = scanner.nextInt();
            scanner.nextLine();  // Clear the newline character

            WeeklySchedule schedule = new WeeklySchedule(new ArrayList<>(Arrays.asList(daysOfWeek)), startHour, endHour);

            // Create a new class
            Class newClass = new Class(className, classroom, selectedTeacher, schedule);

            // List available students
            List<Student> students = university.getStudents();
            System.out.println("Available students:");
            for (int i = 0; i < students.size(); i++) {
                System.out.println((i + 1) + ". " + students.get(i).getName());
            }

            System.out.print("Select students to add to the class (comma-separated numbers): ");
            String studentChoicesInput = scanner.nextLine();
            String[] studentChoices = studentChoicesInput.split(", ");

            for (String studentChoice : studentChoices) {
                int studentIndex = Integer.parseInt(studentChoice);
                if (studentIndex > 0 && studentIndex <= students.size()) {
                    newClass.addStudent(students.get(studentIndex - 1));
                }
            }

            // Add the new class to the university
            university.addClass(newClass);
            System.out.println("The class has been created, and the students have been added.");
        } else {
            System.out.println("Invalid teacher selection.");
        }
    }

    public static void removeClassByName(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Class name to remove: ");
        String className = scanner.nextLine();

        List<Class> classes = university.getClasses();
        Class classToRemove = null;

        for (Class classObj : classes) {
            if (classObj.getName().equalsIgnoreCase(className)) {
                classToRemove = classObj;
                break;
            }
        }

        if (classToRemove != null) {
            university.removeClass(classToRemove);
            System.out.println("The class '" + className + "' has been removed.");
        } else {
            System.out.println("Class not found.");
        }
    }

    public static void printAllClasses(University university) {
        List<Class> classes = university.getClasses();

        System.out.println("List of classes:");
        for (int i = 0; i < classes.size(); i++) {
            System.out.println((i + 1) + ". " + classes.get(i).getName());
        }
    }
}
