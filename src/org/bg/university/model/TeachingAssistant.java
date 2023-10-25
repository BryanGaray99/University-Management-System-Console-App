package org.bg.university.model;

public class TeachingAssistant extends Student {
    private String role;

    public TeachingAssistant(String name, int age, String studentId, String role) {
        super(name, age, studentId);
        this.role = role;
    }

    public String getRole() {
        return role;
    }
}