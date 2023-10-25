package org.bg.university.model;

import java.util.ArrayList;
import java.util.List;

public class Class implements StudentClass {
    private String name;
    private String classroom;
    private Teacher teacher;
    private List<Student> students;
    private WeeklySchedule weeklySchedule;

    public Class(String name, String classroom, Teacher teacher, WeeklySchedule weeklySchedule) {
        this.name = name;
        this.classroom = classroom;
        this.teacher = teacher;
        this.students = new ArrayList<>();
        this.weeklySchedule = weeklySchedule;
    }

    @Override
    public void addStudent(Student student) {
        students.add(student);
    }

    @Override
    public void removeStudent(Student student) {
        students.remove(student);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public List<Student> getStudents() {
        return students;
    }

    public WeeklySchedule getWeeklySchedule() {
        return weeklySchedule;
    }
}
