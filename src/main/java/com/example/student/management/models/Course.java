package com.example.student.management.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import java.sql.Date;

@Entity
public class Course {
    @Id
    private int id;
    @Column(nullable = false, length = 32)
    private String name;
    @Column()
    private Date startDay;
    @Column()
    private Date endDay;
    @JoinColumn(nullable = false)
    private int departmentId;

    public Course() {}

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
