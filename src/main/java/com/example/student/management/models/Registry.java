package com.example.student.management.models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Registry {
    @Id
    private int id;
    @JoinColumn(nullable = false)
    private int studentId;
    @JoinColumn(nullable = false)
    private int courseId;

    public Registry() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
