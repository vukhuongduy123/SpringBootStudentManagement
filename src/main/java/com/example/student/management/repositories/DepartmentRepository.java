package com.example.student.management.repositories;

import com.example.student.management.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    List<Department> findByName(String name);
    @Query("select count(D.id) from Department as D")
    int countAllId();

    @Modifying
    @Transactional
    @Query(value = "insert into Department (id,name) values (:id,:name)", nativeQuery = true)
    int insert(@Param("id") int id, @Param("name") String name);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    @Query(value = "update Department set Department.name = :name, where Department.id = :id", nativeQuery = true)
    int update(@Param("id") int id,@Param("name") String name);
}
