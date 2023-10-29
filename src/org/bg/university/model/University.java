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

    public List<Teacher> getTeachers() {
        return new ArrayList<>(teachers);
    }
    public void addFullTimeTeacher(FullTimeTeacher fullTimeTeacher) {
        teachers.add(fullTimeTeacher);
    }
    public void addPartTimeTeacher(PartTimeTeacher partTimeTeacher) {
        teachers.add(partTimeTeacher);
    }

    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }
    public void addStudent(Student student) {
        students.add(student);
    }

    public List<Class> getClasses() {
        return new ArrayList<>(classes);
    }
    public void addClass(Class classObj) {
        classes.add(classObj);
    }

}