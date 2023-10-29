package org.bg.university.view;

import java.util.Scanner;
import org.bg.university.controller.UniversityController;
import org.bg.university.controller.TeacherController;
import org.bg.university.controller.StudentController;
import org.bg.university.controller.ClassController;
import org.bg.university.model.University;

public class ModifyView {
    public static void showModifyMenu(University university) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*** Modify Menu ***");
            System.out.println("1. Create/Add");
            System.out.println("2. Change Status");
            System.out.println("3. Back to Main Menu");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    showCreateAddMenu(university);
                    break;
                case 2:
                    showChangeStatusMenu(university);
                    break;
                case 3:
                    System.out.println("Returning to the Main Menu.");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    private static void showCreateAddMenu(University university) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*** Create/Add Menu ***");
            System.out.println("1. Teacher");
            System.out.println("2. Student");
            System.out.println("3. Class");
            System.out.println("4. Add Student to Class");
            System.out.println("5. Back to Modify Menu");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    TeacherController.createTeacher(university);
                    break;
                case 2:
                    StudentController.createStudent(university);
                    break;
                case 3:
                    ClassController.createAndAddClass(university);
                    break;
                case 4:
                    UniversityController.addStudentToClass(university);
                    break;
                case 5:
                    System.out.println("Returning to the Modify Menu.");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }

    private static void showChangeStatusMenu(University university) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*** Change Status (Active/Inactive) Menu ***");
            System.out.println("1. Teacher");
            System.out.println("2. Student");
            System.out.println("3. Class");
            System.out.println("4. Back to Modify Menu");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    TeacherController.changeStatusTeacherById(university);
                    break;
                case 2:
                    StudentController.changeStatusStudentById(university);
                    break;
                case 3:
                    ClassController.changeStatusClassById(university);
                    break;
                case 4:
                    System.out.println("Returning to the Modify Menu.");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
}
