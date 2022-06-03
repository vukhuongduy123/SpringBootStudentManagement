package com.example.student.management.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Course {
    @Id
    private int id;
    @Column(nullable = false, unique = false, length = 32)
    private String name;
    @Column(nullable = true, unique = false)
    private Date startDay;
    @Column(nullable = true, unique = false)
    private Date endDay;
    @Column(nullable = false,unique = false)
    private int departmentId;

    public Course() {}

    public Course(int id, String name, Date startDay, Date endDay, int departmentId) {
        this.id = id;
        this.name = name;
        this.startDay = startDay;
        this.endDay = endDay;
        this.departmentId = departmentId;
    }

    public int getId() {
        return id;
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

    public Date getStartDay() {
        return startDay;
    }

    public void setStartDay(Date startDay) {
        this.startDay = startDay;
    }

    public Date getEndDay() {
        return endDay;
    }

    public void setEndDay(Date endDay) {
        this.endDay = endDay;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(int departmentId) {
        this.departmentId = departmentId;
    }


}
