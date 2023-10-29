package org.bg.university.model;

import org.bg.university.utils.IdGenerator;

import java.util.ArrayList;
import java.util.List;

public class Class implements StudentClass {
    private int classId;
    private String name;
    private String classroom;
    private Teacher teacher;
    private Boolean isActive;
    private List<Student> students;
    private WeeklySchedule weeklySchedule;

    public Class(String name, String classroom, Teacher teacher, WeeklySchedule weeklySchedule) {
        this.classId = IdGenerator.generateClassId();
        this.name = name;
        this.classroom = classroom;
        this.teacher = teacher;
        this.isActive = true;
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

    public int getClassId() {
        return classId;
    }

    public String getName() {
        return name;
    }

    public String getClassroom() {
        return classroom;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }
}
