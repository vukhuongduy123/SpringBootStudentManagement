package com.example.student.management.service;

import com.example.student.management.models.Course;
import com.example.student.management.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }

    public Optional<Course> getCourseById(@PathVariable int id) {
        return courseRepository.findById(id);
    }

    public List<Course> getCoursesByName(@PathVariable String name) {
        return courseRepository.findByName(name);
    }

    public Map<Integer,String> countAllCourses(){
        return courseRepository.countAllId();
    }

    public int insertCourse(Course course) {
        return courseRepository.insert(
                course.getId(),course.getName(),course.getStartDay(),course.getEndDay(),course.getDepartmentId());
    }

    public int updateCourse(Course course) {
        return courseRepository.update(
                course.getId(),course.getName(),course.getStartDay(),course.getEndDay(),course.getDepartmentId());
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }
}
