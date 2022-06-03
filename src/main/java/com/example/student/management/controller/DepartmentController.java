package com.example.student.management.controller;

import com.example.student.management.models.Department;
import com.example.student.management.models.ResponseObject;
import com.example.student.management.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path ="/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentRepository departmentRepository;

    @GetMapping("/getAllDepartments")
    ResponseEntity<ResponseObject> getAllDepartments() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(departmentRepository.findAll(), "List of departments", ResponseObject.Status.STATUS_OK));
    }

}
