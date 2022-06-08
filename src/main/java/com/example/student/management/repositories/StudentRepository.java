package com.example.student.management.repositories;

import com.example.student.management.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface StudentRepository extends JpaRepository<Student,Integer> {
    List<Student> findByName(String name);
    List<Student> findByDepartmentId(int departmentId);

    @Modifying
    @Transactional
    @Query(value = "insert into Student (id,name,gender,dob,departmentId) values (:id,:name,:gender,:dob,:departmentId)",
            nativeQuery = true)
    int insert(@Param("id") int id, @Param("name") String name, @Param("gender") boolean gender, @Param("dob") Date dob,
                   @Param("departmentId") int departmentId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    @Query(value = "update Student set Student.name = :name, Student.gender = :gender, Student.dob =:dob,Student.departmentId = :departmentId " +
            "where Student.id = :id", nativeQuery = true)
    int update(@Param("id") int id, @Param("name") String name, @Param("gender") boolean gender, @Param("dob") Date dob,
               @Param("departmentId") int departmentId);

    @Query("select count(S.id) as NumberOfStudents from Student as S")
    Map<Integer,String> countAll();

    @Procedure(name = "CountStudentsFromDeparted", procedureName = "dbo.CountStudentsFromDeparted")
    List<Object> CountStudentsFromDeparted(@Param("departmentId") int departmentId);
}
