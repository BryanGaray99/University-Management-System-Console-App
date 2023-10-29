package org.bg.university.controller;

import java.util.Scanner;
import java.util.List;

import org.bg.university.model.FullTimeTeacher;
import org.bg.university.model.PartTimeTeacher;
import org.bg.university.model.Teacher;
import org.bg.university.model.University;

public class TeacherController {
    public static void createTeacher(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create a new teacher:");
        System.out.print("Teacher name: ");
        String name = scanner.nextLine();
        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Teacher type (Full-Time or Part-Time): ");
        String teacherType = scanner.nextLine();

        if (teacherType.equalsIgnoreCase("Full-Time")) {
            System.out.print("Base salary: ");
            double baseSalary = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Years of experience: ");
            int experienceYears = scanner.nextInt();
            scanner.nextLine();

            university.addFullTimeTeacher(new FullTimeTeacher(name, age, experienceYears, baseSalary));
            System.out.println("The teacher has been created.");
        } else if (teacherType.equalsIgnoreCase("Part-Time")) {
            System.out.print("Base salary: ");
            double baseSalary = scanner.nextDouble();
            scanner.nextLine();
            System.out.print("Active hours per week: ");
            int activeHoursPerWeek = scanner.nextInt();
            scanner.nextLine();

            university.addPartTimeTeacher(new PartTimeTeacher(name, age, baseSalary, activeHoursPerWeek));
            System.out.println("The teacher has been created.");
        } else {
            System.out.println("Invalid teacher type. Please enter 'Full-Time' or 'Part-Time'.");
        }
    }

    public static Teacher findTeacherById(University university, int teacherId) {
        for (Teacher teacher : university.getTeachers()) {
            if (teacher.getEmployeeId() == teacherId) {
                return teacher;
            }
        }
        return null;
    }

    public static void printActiveTeachers(University university) {
        List<Teacher> teachers = university.getTeachers();

        if (teachers.isEmpty()) {
            System.out.println("There are no teachers.");
            return;
        }

        System.out.println("List of Active Teachers:");
        for (Teacher teacher : teachers) {
            if (teacher.isActive()) {
                System.out.println("ID: " + teacher.getEmployeeId());
                System.out.println("Name: " + teacher.getName());
                System.out.println("Age: " + teacher.getAge());

                if (teacher instanceof FullTimeTeacher) {
                    FullTimeTeacher fullTimeTeacher = (FullTimeTeacher) teacher;
                    System.out.println("Type: Full-Time Teacher");
                    System.out.printf("Salary: %.2f\n", fullTimeTeacher.calculateSalary());
                } else if (teacher instanceof PartTimeTeacher) {
                    PartTimeTeacher partTimeTeacher = (PartTimeTeacher) teacher;
                    System.out.println("Type: Part-Time Teacher");
                    System.out.printf("Salary: %.2f\n", partTimeTeacher.calculateSalary());
                }

                System.out.println("-------------------------------");
            }
        }
    }

    public static void changeStatusTeacherById(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Teacher ID to change status: ");
        int teacherId = Integer.parseInt(scanner.nextLine());

        Teacher teacher = findTeacherById(university, teacherId);

        if (teacher != null) {
            boolean isActive = teacher.isActive();
            teacher.setActive(!isActive);
            System.out.println("Teacher " + teacher.getName() + " with ID '" + teacherId + "' has been set as " + (isActive ? "Inactive." : "Active."));
        } else {
            System.out.println("Teacher not found.");
        }
    }
}