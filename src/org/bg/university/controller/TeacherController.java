package org.bg.university.controller;

import java.util.Scanner;
import java.util.List;

import org.bg.university.model.FullTimeTeacher;
import org.bg.university.model.PartTimeTeacher;
import org.bg.university.model.Teacher;
import org.bg.university.model.University;

public class TeacherController {

    // Public methods
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
            createFullTimeTeacher(university, name, age);
        } else if (teacherType.equalsIgnoreCase("Part-Time")) {
            createPartTimeTeacher(university, name, age);
        } else {
            System.out.println("Invalid teacher type. Please enter 'Full-Time' or 'Part-Time'.");
        }
    }

    public static Teacher selectActiveTeacher(University university) {
        Scanner scanner = new Scanner(System.in);

        List<Teacher> teachers = university.getTeachers();

        if (teachers.isEmpty()) {
            System.out.println("There are no teachers available.");
            return null;
        }

        ListActiveTeachers(teachers);

        System.out.print("Select a teacher for the class (number): ");
        int teacherChoice = scanner.nextInt();
        scanner.nextLine();

        if (teacherChoice > 0 && teacherChoice <= teachers.size()) {
            return teachers.get(teacherChoice - 1);
        } else {
            System.out.println("Invalid teacher selection.");
            return null;
        }
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
                printTeacherInfo(teacher);
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
            toggleTeacherStatus(teacher);
            System.out.println("Teacher " + teacher.getName() + " with ID '" + teacherId + "' has been set as " + (teacher.isActive() ? "Active." : "Inactive."));
        } else {
            System.out.println("Teacher not found.");
        }
    }

    // Private Methods
    private static void createFullTimeTeacher(University university, String name, int age) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Base salary: ");
        double baseSalary = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Years of experience: ");
        int experienceYears = scanner.nextInt();
        scanner.nextLine();

        university.addFullTimeTeacher(new FullTimeTeacher(name, age, experienceYears, baseSalary));
        System.out.println("The teacher has been created.");
    }

    private static void createPartTimeTeacher(University university, String name, int age) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Base salary: ");
        double baseSalary = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Active hours per week: ");
        int activeHoursPerWeek = scanner.nextInt();
        scanner.nextLine();

        university.addPartTimeTeacher(new PartTimeTeacher(name, age, baseSalary, activeHoursPerWeek));
        System.out.println("The teacher has been created.");
    }

    private static Teacher findTeacherById(University university, int teacherId) {
        for (Teacher teacher : university.getTeachers()) {
            if (teacher.getEmployeeId() == teacherId) {
                return teacher;
            }
        }
        return null;
    }

    private static void ListActiveTeachers(List<Teacher> teachers) {
        System.out.println("Active teachers:");
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).isActive()) {
                System.out.println((i + 1) + ". " + teachers.get(i).getName());
            }
        }
    }

    private static void printTeacherInfo(Teacher teacher) {
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
    }

    private static void toggleTeacherStatus(Teacher teacher) {
        boolean isActive = teacher.isActive();
        teacher.setActive(!isActive);
    }

}
