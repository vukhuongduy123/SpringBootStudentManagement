package com.example.student.management.repositories;

import com.example.student.management.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.List;
import java.util.Map;

@Repository
public interface CourseRepository extends JpaRepository<Course, Integer> {
    List<Course> findByName(String name);


    @Query("select count(C.id) as NumberOfCourses from Course as C")
    Map<Integer, String> countAllId();

    @Modifying
    @Transactional
    @Query(value = "insert into Course (id,name,startDay,endDay,departmentId) values (:id,:name,:startDay,:endDay,:departmentId)",
            nativeQuery = true)
    int insert(@Param("id") int id, @Param("name") String name, @Param("startDay") Date startDay, @Param("endDay") Date endDay,
               @Param("departmentId") int departmentId);

    @Modifying(flushAutomatically = true, clearAutomatically = true)
    @Transactional
    @Query(value = "update Course set Course.name = :name, Course.startDay = :startDay, Course.endDay =:endDay,Course.departmentId = :departmentId " +
            "where Course.id = :id", nativeQuery = true)
    int update(@Param("id") int id, @Param("name") String name, @Param("startDay") Date startDay, @Param("endDay") Date endDay,
               @Param("departmentId") int departmentId);
}
