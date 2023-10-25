package org.bg.university.model;

import java.util.ArrayList;
import java.util.List;
public class WeeklySchedule {
    private String daysOfWeek;
    private int startHour;
    private int endHour;

    public WeeklySchedule(String daysOfWeek, int startHour, int endHour) {
        this.daysOfWeek = daysOfWeek;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public String getDaysOfWeek() {
        return daysOfWeek;
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
}
