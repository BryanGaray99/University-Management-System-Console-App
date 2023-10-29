package org.bg.university.model;

import java.util.List;

/**
 * @author Bryan Garay
 */
public interface StudentClass {
    void addStudent(Student student);
    void removeStudent(Student student);
    Teacher getTeacher();
    List<Student> getStudents();
}