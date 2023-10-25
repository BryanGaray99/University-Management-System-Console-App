package org.bg.university.model;

import java.util.ArrayList;
import java.util.List;

public class University {
    private List<Teacher> teachers;
    private List<Student> students;
    private List<Class> classes;

    public University() {
        teachers = new ArrayList<>();
        students = new ArrayList<>();
        classes = new ArrayList<>();
    }

    public void addTeacher(Teacher teacher) {
        teachers.add(teacher);
    }

    public void removeTeacher(Teacher teacher) {
        teachers.remove(teacher);
    }

    public List<Teacher> getTeachers() {
        return new ArrayList<>(teachers);
    }

    public void addStudent(Student student) {
        students.add(student);
    }

    public void removeStudent(Student student) {
        students.remove(student);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }

    public void addClass(Class classObj) {
        classes.add(classObj);
    }

    public void removeClass(Class classObj) {
        classes.remove(classObj);
    }

    public List<Class> getClasses() {
        return new ArrayList<>(classes);
    }
}