package com.example.student.management.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

@Entity
public class Users {
    @Id
    int id;
    @Column
    String name;
    @Column
    String pass;
    @Column
    String roles;
    @JoinColumn
    int studentId;

    public Users(int id, String name, String pass, String roles, int studentId) {
        this.id = id;
        this.name = name;
        this.pass = pass;
        this.roles = roles;
        this.studentId = studentId;
    }

    public Users() {}

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

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
