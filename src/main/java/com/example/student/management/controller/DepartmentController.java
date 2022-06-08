package com.example.student.management.controller;

import com.example.student.management.models.Department;
import com.example.student.management.models.ResponseObject;
import com.example.student.management.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path ="/api/department")
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/getAllDepartments")
    ResponseEntity<ResponseObject> getAllDepartments() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(departmentService.getAllDepartments(), "List of departments", ResponseObject.Status.STATUS_OK));
    }

    @GetMapping("/getDepartmentById/{id}")
    ResponseEntity<ResponseObject> getDepartmentById(@PathVariable int id) {
        Optional<Department> department = departmentService.getDepartmentById(id);

        return department.isPresent() ? ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject(department, "Department found", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Department not found", ResponseObject.Status.STATUS_FAILED));
    }

    @GetMapping("/getDepartmentByName/{name}")
    ResponseEntity<ResponseObject> getDepartmentByName(@PathVariable String name) {
        List<Department> department = departmentService.getDepartmentByName(name);

        return department.size() > 0 ? ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject(department, "Departments found", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Departments not found", ResponseObject.Status.STATUS_FAILED));
    }

    @GetMapping("/countAllDepartments")
    ResponseEntity<ResponseObject> countAllDepartments() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(departmentService.countAllDepartments(),
                        "Number of departments", ResponseObject.Status.STATUS_OK));
    }

    @PostMapping("/insertDepartment")
    ResponseEntity<ResponseObject> insertDepartment(@RequestBody Department department) {
        return departmentService.insertDepartment(department) > 0
                ? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(departmentService.insertDepartment(department),
                "Department's inserted", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(department, "Department's already existed",
                ResponseObject.Status.STATUS_FAILED));
    }

    @PutMapping("/updateDepartment")
    ResponseEntity<ResponseObject> updateDepartment(@RequestBody Department department) {
        return departmentService.updateDepartment(department) > 0
                ? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(departmentService.updateDepartment(department),
                "Department's updated", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(department, "Department's not existed",
                ResponseObject.Status.STATUS_FAILED));
    }

    @DeleteMapping("deleteDepartmentById/{id}")
    ResponseEntity<ResponseObject> deleteDepartment(@PathVariable int id) {
        departmentService.deleteDepartment(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject("", "department's deleted", ResponseObject.Status.STATUS_OK));
    }
}
