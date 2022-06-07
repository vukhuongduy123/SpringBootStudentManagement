package com.example.student.management.repositories;

import com.example.student.management.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByName(String name);
    @Query("select count(D.id) from Department as D")
    int countAllId();
}
