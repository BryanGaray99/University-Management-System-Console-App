package org.bg.university.view;

import org.bg.university.model.University;

import java.util.Scanner;

/**
 * This class is responsible for displaying the main menu of the program.
 * @author Bryan Garay
 */
public class MainView {
    public static void showMainMenu(University university) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome to the University Tracking System!");

        // Initialize the University object here

        while (true) {
            System.out.println("\n*** Main Menu ***");
            System.out.println("1. Modify Registry");
            System.out.println("2. Obtain Information");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    ModifyView.showModifyMenu(university);
                    break;
                case 2:
                    ReadView.showReadMenu(university);
                    break;
                case 3:
                    System.out.println("Exiting the program.");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
}
