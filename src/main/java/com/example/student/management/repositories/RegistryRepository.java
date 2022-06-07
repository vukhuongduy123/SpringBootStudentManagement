package com.example.student.management.repositories;

import com.example.student.management.models.Registry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RegistryRepository extends JpaRepository<Registry,Integer> {
    @Modifying(clearAutomatically=true, flushAutomatically=true)
    @Query(value = "{call RegisterCourse(:studentId,:courseId)}",nativeQuery = true)
    @Transactional
    int RegisterCourse(@Param("studentId") int studentId, @Param("courseId") int courseId);

}
