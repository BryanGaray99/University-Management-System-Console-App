package org.bg.university.model;

import org.bg.university.utils.IdGenerator;

public class Student extends User {
    private int studentId;

    public Student(String name, int age) {
        super(name, age);
        this.studentId = IdGenerator.generateStudentId();
    }

    public int getStudentId() {
        return studentId;
    }
}