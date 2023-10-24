package org.bg.university.model;

import java.util.ArrayList;
import java.util.List;
public class WeeklySchedule {
    private List<String> daysOfWeek;
    private int startHour;
    private int endHour;

    public WeeklySchedule(List<String> daysOfWeek, int startHour, int endHour) {
        this.daysOfWeek = new ArrayList<>(daysOfWeek);
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public List<String> getDaysOfWeek() {
        return new ArrayList<>(daysOfWeek);
    }

    public int getStartHour() {
        return startHour;
    }

    public int getEndHour() {
        return endHour;
    }
}
