package org.bg.university.controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import org.bg.university.model.Class;
import org.bg.university.model.Teacher;
import org.bg.university.model.Student;
import org.bg.university.model.WeeklySchedule;
import org.bg.university.model.University;

public class ClassController {
    // Public methods
    public static void createAndAddClass(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Create a new class:");

        System.out.print("Class name: ");
        String className = scanner.nextLine();
        System.out.print("Classroom: ");
        String classroom = scanner.nextLine();

        Teacher selectedTeacher = TeacherController.selectActiveTeacher(university);
        if (selectedTeacher == null) {
            return;
        }

        WeeklySchedule schedule = ScheduleController.createWeeklySchedule();
        if (schedule == null) {
            return;
        }

        Class newClass = new Class(className, classroom, selectedTeacher, schedule);

        boolean addStudents = true;

        do {
            Student selectedStudent = StudentController.selectStudent(university);
            if (selectedStudent != null) {
                addStudentToClass(newClass, selectedStudent);
            } else {
                addStudents = false;
            }
        } while (addStudents);

        university.addClass(newClass);
        System.out.println("The class has been created, and the students have been added.");
    }

    public static void printActiveClasses(University university) {
        List<Class> classes = university.getClasses();
        Scanner scanner = new Scanner(System.in);

        if (classes.isEmpty()) {
            System.out.println("There are no classes.");
            return;
        }

        printListActiveClasses(classes);

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

    public static void changeStatusClassById(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("ID of the class to change status: ");
        int classId = Integer.parseInt(scanner.nextLine());

        Class classObj = findClassById(university, classId);

        if (classObj != null) {
            toggleClassStatus(classObj);
            System.out.println("Class " + classObj.getName() + " with ID '" + classId + "' has been set as " + (classObj.isActive() ? "Active." : "Inactive."));
        } else {
            System.out.println("Class not found.");
        }
    }

    public static List<Class> getActiveClasses(University university) {
        List<Class> activeClasses = new ArrayList<>();
        for (Class classObj : university.getClasses()) {
            if (classObj.isActive()) {
                activeClasses.add(classObj);
            }
        }
        return activeClasses;
    }

    public static void printListActiveClasses(List<Class> classes) {
        System.out.println("List of active classes:");
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).isActive()) {
                System.out.println((i + 1) + ". " + classes.get(i).getName());
            }
        }
    }

    public static void addStudentToClass(Class classObj, Student selectedStudent) {
        if (classObj.getStudents().contains(selectedStudent)) {
            System.out.println(selectedStudent.getName() + " is already in the class.");
        } else {
            classObj.addStudent(selectedStudent);
            System.out.println(selectedStudent.getName() + " has been added to the class.");
        }
    }

    // Private methods
    private static Class findClassById(University university, int classId) {
        for (Class classObj : university.getClasses()) {
            if (classObj.getClassId() == classId) {
                return classObj;
            }
        }
        return null;
    }

    private static void printClassDetails(Class classObj) {
        System.out.println("Class Name: " + classObj.getName());
        System.out.println("Classroom: " + classObj.getClassroom());
        System.out.println("Weekly Schedule: " + classObj.getWeeklySchedule());
        if (classObj.getTeacher().isActive()) {
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

    private static void toggleClassStatus(Class classObj) {
        boolean isActive = classObj.isActive();
        classObj.setActive(!isActive);
    }

}
