package org.bg.university.controller;

import java.util.Scanner;
import java.util.List;

import org.bg.university.model.*;
import org.bg.university.validation.StudentInputValidations;
import org.bg.university.validation.TeacherInputValidations;

/**
 * TeacherController is a class that contains methods to create, select, and change the status of a teacher.
 * @author Bryan Garay
 */
public class TeacherController {
    // Public methods
    /**
     * Creates a new teacher and adds it to the university.
     * @param university
     */
    public static void createTeacher(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create a new teacher:");
        System.out.print("Teacher name: ");
        String name = scanner.nextLine();
        // Validations
        String nameError = TeacherInputValidations.validateName(name);
        if (nameError != null) {
            System.out.println(nameError);
            return;
        }

        System.out.print("Age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        // Validations
        String ageError = TeacherInputValidations.validateAge(age);
        if (ageError != null) {
            System.out.println(ageError);
            return;
        }

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

    /**
     * Selects an active teacher from the university.
     * @param university The university to select the teacher from.
     * @return The selected teacher, or null if no teachers are available.
     */
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

    /**
     * Prints the list of active teachers.
     * @param university The university to select the teacher from.
     */
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

    /**
     * Changes the status of a teacher.
     * @param university The university to select the teacher from.
     */
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

    /**
     * Creates a new full-time teacher and adds it to the university.
     * @param university The university to select the teacher from.
     * @param name The name of the teacher.
     * @param age The age of the teacher.
     */
    // Private Methods
    private static void createFullTimeTeacher(University university, String name, int age) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Base salary: ");
        double baseSalary = scanner.nextDouble();
        scanner.nextLine();
        // Validations
        String baseSalaryError = TeacherInputValidations.isBaseSalaryValid(baseSalary);
        if (baseSalaryError != null) {
            System.out.println(baseSalaryError);
            return;
        }

        System.out.print("Years of experience: ");
        int experienceYears = scanner.nextInt();
        scanner.nextLine();
        // Validations
        String experienceYearsError = TeacherInputValidations.isExperienceYearsValid(experienceYears);
        if (experienceYearsError != null) {
            System.out.println(experienceYearsError);
            return;
        }

        FullTimeTeacher newFullTimeTeacher = new FullTimeTeacher(name, age, experienceYears, baseSalary);

        university.addFullTimeTeacher(newFullTimeTeacher);
        System.out.println("The teacher has been created.");
    }

    /**
     * Creates a new part-time teacher and adds it to the university.
     * @param university The university to select the teacher from.
     * @param name The name of the teacher.
     * @param age The age of the teacher.
     */
    private static void createPartTimeTeacher(University university, String name, int age) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Base salary: ");
        double baseSalary = scanner.nextDouble();
        scanner.nextLine();
        // Validations
        String baseSalaryError = TeacherInputValidations.isBaseSalaryValid(baseSalary);
        if (baseSalaryError != null) {
            System.out.println(baseSalaryError);
            return;
        }

        System.out.print("Active hours per week: ");
        int activeHoursPerWeek = scanner.nextInt();
        scanner.nextLine();
        // Validations
        String activeHoursPerWeekError = TeacherInputValidations.isActiveHoursPerWeekValid(activeHoursPerWeek);
        if (activeHoursPerWeekError != null) {
            System.out.println(activeHoursPerWeekError);
            return;
        }

        PartTimeTeacher newPartTimeTeacher = new PartTimeTeacher(name, age, baseSalary, activeHoursPerWeek);
        university.addPartTimeTeacher(newPartTimeTeacher);
        System.out.println("The teacher has been created.");
    }

    /**
     * @param university The university to select the teacher from.
     * @param teacherId The ID of the teacher to find.
     * @return The teacher with the given ID, or null if no teacher is found.
     */
    private static Teacher findTeacherById(University university, int teacherId) {
        for (Teacher teacher : university.getTeachers()) {
            if (teacher.getEmployeeId() == teacherId) {
                return teacher;
            }
        }
        return null;
    }

    /**
     * Prints the list of active teachers.
     * @param teachers The list of teachers to print.
     */
    private static void ListActiveTeachers(List<Teacher> teachers) {
        System.out.println("Active teachers:");
        for (int i = 0; i < teachers.size(); i++) {
            if (teachers.get(i).isActive()) {
                System.out.println((i + 1) + ". " + teachers.get(i).getName());
            }
        }
    }

    /**
     * Prints the information of a teacher.
     * @param teacher The teacher to print.
     */
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

    /**
     * Toggles the status of a teacher.
     * @param teacher The teacher to toggle the status of.
     */
    private static void toggleTeacherStatus(Teacher teacher) {
        boolean isActive = teacher.isActive();
        teacher.setActive(!isActive);
    }

}
