package com.example.student.management.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Student {
    @Id
    private int id;
    @Column(nullable = false, length = 32)
    private String name;
    @Column(nullable = false)
    private boolean gender;
    @Column(nullable = false, length = 64)
    private Date dob;
    @JoinColumn(nullable = false)
    private int departmentId;

    public Student() {}

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isGender() {
        return gender;
    }

    public void setGender(boolean gender) {
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public Date getDob() {
        return dob;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", gender=" + gender +
                ", dob='" + dob + '\'' +
                ", departmentId=" + departmentId +
                '}';
    }
}
