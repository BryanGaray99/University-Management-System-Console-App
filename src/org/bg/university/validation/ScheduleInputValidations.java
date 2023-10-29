package org.bg.university.validation;

import java.time.Duration;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * ScheduleInputValidations is a class that contains methods to validate schedule input data.
 * @author Bryan Garay
 */
public class ScheduleInputValidations {
    /**
     * Checks if a time is in the 'HH:mm' format.
     * @param time A string representing a time in the 'HH:mm' format.
     * @return True if the time is in the 'HH:mm' format, false otherwise.
     */
    // Private methods
    public static boolean isValidTimeFormat(String time) {
        try {
            LocalTime.parse(time, DateTimeFormatter.ofPattern("HH:mm"));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Validates that the start time is before or equal to the end time.
     * @param startTime The start time in 'HH:mm' format.
     * @param endTime The end time in 'HH:mm' format.
     * @return True if the times are valid, false otherwise.
     */
    public static boolean isValidTimeRange(String startTime, String endTime) {
        LocalTime start = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime end = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));

        if (start.isBefore(end) || start.equals(end)) {
            return true;
        } else {
            System.out.println("Start time should be before or equal to end time.");
            return false;
        }
    }

    /**
     * Validates that the difference between start and end time is less than or equal to 3 hours.
     * @param startTime The start time in 'HH:mm' format.
     * @param endTime The end time in 'HH:mm' format.
     * @return True if the times are valid, false otherwise.
     */
    public static boolean isValidTimeDifference(String startTime, String endTime) {
        LocalTime start = LocalTime.parse(startTime, DateTimeFormatter.ofPattern("HH:mm"));
        LocalTime end = LocalTime.parse(endTime, DateTimeFormatter.ofPattern("HH:mm"));

        long hoursDifference = Duration.between(start, end).toHours();

        if (hoursDifference <= 3) {
            return true;
        } else {
            System.out.println("The difference between start and end time should be less than or equal to 3 hours.");
            return false;
        }
    }
}
