package com.example.student.management.service;

import com.example.student.management.models.Department;
import com.example.student.management.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Autowired
    private StudentService studentService;

    public List<Department> getAllDepartments() {
        return departmentRepository.findAll();
    }

    public Optional<Department> getDepartmentById(int id) {
        return departmentRepository.findById(id);
    }

    public List<Department> getDepartmentByName(String name) {
        return departmentRepository.findByName(name);
    }

    public int countAllDepartments(){
        return departmentRepository.countAllId();
    }

    public int insertDepartment(Department department) {
        return departmentRepository.insert(department.getId(),department.getName());
    }

    public int updateDepartment(Department department) {
        return departmentRepository.update(department.getId(),department.getName());
    }

    public void deleteDepartment(int id) {
            departmentRepository.deleteById(id);
    }

    public boolean isExistedId(int id) {
        return departmentRepository.existsById(id);
    }

    public StudentService getStudentService() {
        return studentService;
    }
}

