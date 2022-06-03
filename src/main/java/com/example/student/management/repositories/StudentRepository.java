package com.example.student.management.repositories;

import com.example.student.management.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByName(String name);
    List<Student> findByDepartmentId(int departmentId);
    @Query("select count(S.id) from Student as S")
    int countAllId();

}
