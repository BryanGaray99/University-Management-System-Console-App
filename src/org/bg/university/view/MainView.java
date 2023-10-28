package org.bg.university.view;

import org.bg.university.model.University;

import java.util.Scanner;

public class MainView {
    public static void showMainMenu(University university) {
        Scanner scanner = new Scanner(System.in);
        final String PASSWORD = "1";

        System.out.print("Enter the password to access the system: ");
        String password = scanner.nextLine();

        if (password.equals(PASSWORD)) {
            System.out.println("Access granted. Welcome to the University Tracking System!");

            // Initialize the University object here

            while (true) {
                System.out.println("\n*** Main Menu ***");
                System.out.println("1. Modify Registry");
                System.out.println("2. Obtain Information");
                System.out.println("3. Exit");
                System.out.print("Select an option: ");

                int option = scanner.nextInt();
                scanner.nextLine();  // Clear the newline character

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
        } else {
            System.out.println("Access denied. Incorrect password.");
        }
    }
}
