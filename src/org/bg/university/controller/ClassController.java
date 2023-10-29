package org.bg.university.controller;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;
import org.bg.university.model.Class;
import org.bg.university.model.Teacher;
import org.bg.university.model.Student;
import org.bg.university.model.WeeklySchedule;
import org.bg.university.model.University;

/**
 * This class contains methods to create and manage classes.
 * @version 1.0
 * @author Bryan Garay
 */
public class ClassController {
    // Public methods
    /**
     * This method creates a new class and adds it to the university object.
     * @param university The university object to add the class to.
     */
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

    /**
     * This method prints the list of active classes and allows the user to select a class to view its details.
     * @param university The university object to select the class from.
     */
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

    /**
     * This method change the status of a class (active/inactive).
     * @param university The university object to select the class from.
     */
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

    /**
     * This method returns a list of active classes.
     * @param university The university object to select the class from.
     * @return A list of active classes.
     */
    public static List<Class> getActiveClasses(University university) {
        List<Class> activeClasses = new ArrayList<>();
        for (Class classObj : university.getClasses()) {
            if (classObj.isActive()) {
                activeClasses.add(classObj);
            }
        }
        return activeClasses;
    }

    /**
     * This method prints the list of active classes.
     * @param classes The list of classes to print.
     */
    public static void printListActiveClasses(List<Class> classes) {
        System.out.println("List of active classes:");
        for (int i = 0; i < classes.size(); i++) {
            if (classes.get(i).isActive()) {
                System.out.println((i + 1) + ". " + classes.get(i).getName());
            }
        }
    }

    /**
     * This method adds a student to a class.
     * @param classObj The class object to add the student to.
     * @param selectedStudent The student object to add to the class.
     */
    public static void addStudentToClass(Class classObj, Student selectedStudent) {
        if (classObj.getStudents().contains(selectedStudent)) {
            System.out.println(selectedStudent.getName() + " is already in the class.");
        } else {
            classObj.addStudent(selectedStudent);
            System.out.println(selectedStudent.getName() + " has been added to the class.");
        }
    }

    /**
     * @param university The university object to select the class from.
     * @param classId The ID of the class to find.
     * @return The class object with the given ID.
     */
    // Private methods
    private static Class findClassById(University university, int classId) {
        for (Class classObj : university.getClasses()) {
            if (classObj.getClassId() == classId) {
                return classObj;
            }
        }
        return null;
    }

    /**
     * This method prints the details of a class.
     * @param classObj The class object to print.
     */
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

    /**
     * This method toggles the status of a class (active/inactive).
     * @param classObj The class object to toggle the status of.
     */
    private static void toggleClassStatus(Class classObj) {
        boolean isActive = classObj.isActive();
        classObj.setActive(!isActive);
    }

}
