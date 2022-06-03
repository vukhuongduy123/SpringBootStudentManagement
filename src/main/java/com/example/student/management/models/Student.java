package com.example.student.management.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Student {
    @Id
    private int id;
    @Column(nullable = false, unique = false, length = 32)
    private String name;
    @Column(nullable = false, unique = false)
    private boolean gender;
    @Column(nullable = false, unique = false,length = 64)
    private Date dob;

    @Column(nullable = false, unique = false)
    private int departmentId;

    public Student() {}

    public Student(int id, String name, boolean gender, Date dob, int departmentId) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.dob = dob;
        this.departmentId = departmentId;
    }

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
