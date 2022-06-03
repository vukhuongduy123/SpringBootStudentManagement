package com.example.student.management.repositories;
import com.example.student.management.models.Course;
import com.example.student.management.models.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findByName(String name);
    List<Student> findByDepartmentId(int departmentId);
    @Query("select count(C.id) from Course as C")
    int countAllId();
}
