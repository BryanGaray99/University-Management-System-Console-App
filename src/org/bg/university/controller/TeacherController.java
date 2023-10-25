package org.bg.university.controller;

import java.util.Scanner;
import java.util.List;

import org.bg.university.model.FullTimeTeacher;
import org.bg.university.model.PartTimeTeacher;
import org.bg.university.model.Teacher;
import org.bg.university.model.University;

public class TeacherController {
    public static void printAllProfessors(University university) {
        List<Teacher> teachers = university.getTeachers();
        System.out.println("List of professors:");
        for (Teacher teacher : teachers) {
            System.out.println("Name: " + teacher.getName());
            System.out.println("ID: " + teacher.getEmployeeId());
            System.out.println("Age: " + teacher.getAge());
            System.out.println("Salary: " + teacher.calculateSalary());
            System.out.println("-------------------------------");
        }
    }

    public static void createTeacher(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create a new teacher:");
        System.out.print("Teacher name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();  // Clear the newline character
        System.out.print("Employee ID: ");
        int employeeId = scanner.nextInt();

        // Prompt for teacher type (full-time or part-time)
        System.out.print("Teacher type (Full-Time or Part-Time): ");
        String teacherType = scanner.nextLine();

        if (teacherType.equalsIgnoreCase("Full-Time")) {
            System.out.print("Base salary: ");
            double baseSalary = scanner.nextDouble();
            scanner.nextLine();  // Clear the newline character
            System.out.print("Years of experience: ");
            int experienceYears = scanner.nextInt();
            scanner.nextLine();  // Clear the newline character

            university.addFullTimeTeacher(new FullTimeTeacher(name, age, employeeId, experienceYears, baseSalary));
        } else if (teacherType.equalsIgnoreCase("Part-Time")) {
            System.out.print("Hourly wage: ");
            double hourlyWage = scanner.nextDouble();
            scanner.nextLine();  // Clear the newline character
            System.out.print("Active hours per week: ");
            int activeHoursPerWeek = scanner.nextInt();
            scanner.nextLine();  // Clear the newline character

            university.addPartTimeTeacher(new PartTimeTeacher(name, age, employeeId, hourlyWage, activeHoursPerWeek));
        } else {
            System.out.println("Invalid teacher type. Please enter 'Full-Time' or 'Part-Time'.");
        }

        System.out.println("The teacher has been created.");
    }


    public static void removeTeacherById(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Teacher ID to remove: ");
        int teacherId = Integer.parseInt(scanner.nextLine());

        Teacher teacherToRemove = university.findTeacherById(teacherId);

        if (teacherToRemove != null) {
            university.removeTeacher(teacherToRemove);
            System.out.println("The teacher with ID '" + teacherId + "' has been removed.");
        } else {
            System.out.println("Teacher not found.");
        }
    }
}