package org.bg.university.view;

import java.util.Scanner;
import org.bg.university.controller.TeacherController;
import org.bg.university.controller.ClassController;
import org.bg.university.controller.UniversityController;
import org.bg.university.model.University;

public class ReadView {
    public static void showReadMenu(University university) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n*** Read Menu ***");
            System.out.println("1. Print Active Teachers");
            System.out.println("2. Print Active Classes");
            System.out.println("3. List Classes for Student");
            System.out.println("4. Back to Main Menu");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    TeacherController.printActiveTeachers(university);
                    break;
                case 2:
                    ClassController.printActiveClasses(university);
                    break;
                case 3:
                    UniversityController.listClassesForStudent(university);
                    break;
                case 4:
                    System.out.println("Returning to the Main Menu.");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
}
