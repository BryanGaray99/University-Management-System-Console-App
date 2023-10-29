package org.bg.university.controller;

import org.bg.university.model.WeeklySchedule;
import org.bg.university.validation.ScheduleInputValidations;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This class contains methods to create and manage weekly schedules.
 * @author Bryan Garay
 */
public class ScheduleController {
    // Public methods
    /**
     * Creates a new WeeklySchedule object.
     * @return A new WeeklySchedule object.
     */
    public static WeeklySchedule createWeeklySchedule() {
        Scanner scanner = new Scanner(System.in);
        List<String> daysOfWeek = selectDaysOfWeek(scanner);

        if (daysOfWeek.isEmpty()) {
            System.out.println("No days have been selected.");
            return null;
        }

        System.out.print("Start hour (HH:mm): ");
        String startHour = scanner.nextLine();

        System.out.print("End hour (HH:mm): ");
        String endHour = scanner.nextLine();

        if (ScheduleInputValidations.isValidTimeFormat(startHour) && ScheduleInputValidations.isValidTimeFormat(endHour)
                && ScheduleInputValidations.isValidTimeRange(startHour, endHour)
                && ScheduleInputValidations.isValidTimeDifference(startHour, endHour)) {
            return new WeeklySchedule(daysOfWeek, startHour, endHour);
        } else {
            System.out.println("Invalid schedule. Please make sure the times are valid.");
            return null;
        }
    }

    // Private methods
    /**
     * Selects the days of the week.
     * @param scanner A Scanner object to read user input.
     * @return A list of the days of the week.
     */
    private static List<String> selectDaysOfWeek(Scanner scanner) {
        printDaysOfWeek();
        List<String> daysOfWeek = new ArrayList<>();

        int dayChoice;
        do {
            System.out.print("Select a day (0 to finish): ");
            dayChoice = scanner.nextInt();
            scanner.nextLine();

            if (dayChoice == 0) {
                break;
            } else if (dayChoice >= 1 && dayChoice <= 6) {
                String day = getDayFromChoice(dayChoice);
                if (!daysOfWeek.contains(day)) {
                    daysOfWeek.add(day);
                    System.out.println(day + " has been added.");
                } else {
                    System.out.println(day + " is already added.");
                }
            } else {
                System.out.println("Invalid choice. Please select a valid day.");
            }
        } while (true);

        return daysOfWeek;
    }

    /**
     * Prints the days of the week.
     */
    private static void printDaysOfWeek() {
        System.out.println("Days of the week:");
        System.out.println("1. Monday");
        System.out.println("2. Tuesday");
        System.out.println("3. Wednesday");
        System.out.println("4. Thursday");
        System.out.println("5. Friday");
        System.out.println("6. Saturday");
        System.out.println("0. Done");
    }

    /**
     * @param choice The choice of the day.
     * @return The day corresponding to the choice.
     */
    private static String getDayFromChoice(int choice) {
        switch (choice) {
            case 1:
                return "Monday";
            case 2:
                return "Tuesday";
            case 3:
                return "Wednesday";
            case 4:
                return "Thursday";
            case 5:
                return "Friday";
            case 6:
                return "Saturday";
            default:
                return "";
        }
    }
}
