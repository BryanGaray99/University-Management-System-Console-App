package org.bg.university.controller;

import org.bg.university.model.WeeklySchedule;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
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
        System.out.print("Days of the week (e.g., Monday, Tuesday, Wednesday): ");
        String daysOfWeekInput = scanner.nextLine();
        String daysOfWeek = daysOfWeekInput.replaceAll("\\s+", "");

        System.out.print("Start hour (HH:mm): ");
        String startHour = scanner.nextLine();

        System.out.print("End hour (HH:mm): ");
        String endHour = scanner.nextLine();

        if (isValidTimeFormat(startHour) && isValidTimeFormat(endHour)) {
            return new WeeklySchedule(daysOfWeek, startHour, endHour);
        } else {
            System.out.println("Invalid time format. Please use the 'HH:mm' format (e.g., 08:30).");
            return null;
        }
    }

    /**
     * Checks if a time is in the 'HH:mm' format.
     * @param time A string representing a time in the 'HH:mm' format.
     * @return True if the time is in the 'HH:mm' format, false otherwise.
     */
    // Private methods
    private static boolean isValidTimeFormat(String time) {
        try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

}
