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
            System.out.println("1. Create Teacher");
            System.out.println("2. Create Student");
            System.out.println("3. Create Class");
            System.out.println("4. Add Student to Class");
            System.out.println("5. Remove Teacher");
            System.out.println("6. Remove Student");
            System.out.println("7. Remove Class");
            System.out.println("8. Back to Main Menu");
            System.out.print("Select an option: ");

            int option = scanner.nextInt();
            scanner.nextLine();  // Clear the newline character

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
                    TeacherController.removeTeacherById(university);
                    break;
                case 6:
                    StudentController.removeStudentById(university);
                    break;
                case 7:
                    ClassController.removeClassById(university);
                    break;
                case 8:
                    System.out.println("Returning to the Main Menu.");
                    return;
                default:
                    System.out.println("Invalid option. Please select a valid option.");
            }
        }
    }
}
