package com.example.student.management.repositories;
import com.example.student.management.models.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Map;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findByName(String name);
    boolean existsByDepartmentId(int departmentId);
    @Query("select count(C.id) as NumberOfCourses from Course as C")
    Map<Integer,String> countAllId();
}
