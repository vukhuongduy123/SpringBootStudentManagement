package com.example.student.management.repositories;

import com.example.student.management.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByName(String name);
    List<Student> findByDepartmentId(int departmentId);
    boolean existsByDepartmentId(int departmentId);
    @Query("select count(S.id) as NumberOfStudents from Student as S")
    Map<Integer,String> countAllId();

    @Query(value = "{call CountStudentsFromDeparted(:id)}", nativeQuery = true)
    Map<Object,String> CountStudentsFromDeparted(@Param("id") int id);


}
