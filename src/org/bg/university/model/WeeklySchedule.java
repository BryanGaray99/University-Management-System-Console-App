package org.bg.university.model;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class WeeklySchedule {
    private String daysOfWeek;
    private LocalTime startHour;
    private LocalTime endHour;

    public WeeklySchedule(String daysOfWeek, String startHour, String endHour) {
        this.daysOfWeek = daysOfWeek;
        this.startHour = LocalTime.parse(startHour, DateTimeFormatter.ofPattern("HH:mm"));
        this.endHour = LocalTime.parse(endHour, DateTimeFormatter.ofPattern("HH:mm"));
    }

    @Override
    public String toString() {
        return "Days of the Week: " + daysOfWeek + ", Start Hour: " + startHour + ", End Hour: " + endHour;
    }
}