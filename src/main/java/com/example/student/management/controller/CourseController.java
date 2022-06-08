package com.example.student.management.controller;

import com.example.student.management.models.Course;
import com.example.student.management.models.ResponseObject;
import com.example.student.management.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/course")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @GetMapping("/getAllCourses")
    ResponseEntity<ResponseObject> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(courseService.getAllCourses(), "List of courses", ResponseObject.Status.STATUS_OK));
    }

    @GetMapping("/getCourseById/{id}")
    ResponseEntity<ResponseObject> getCourseById(@PathVariable int id) {
        Optional<Course> course = courseService.getCourseById(id);
        return course.isPresent() ? ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject(course, "Course found", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Course not found", ResponseObject.Status.STATUS_FAILED));
    }

    @GetMapping("/getCoursesByName/{name}")
    ResponseEntity<ResponseObject> getCoursesByName(@PathVariable String name) {
        List<Course> courses = courseService.getCoursesByName(name);

        return courses.size() > 0 ? ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject(courses, "Courses found", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Courses not found", ResponseObject.Status.STATUS_FAILED));
    }

    @GetMapping("/countAllCourses")
    ResponseEntity<ResponseObject> countAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(courseService.countAllCourses(),
                "Number of courses", ResponseObject.Status.STATUS_OK));
    }

    @PostMapping("/insertCourse")
    ResponseEntity<ResponseObject> insertCourse(@RequestBody Course course) {
        return courseService.insertCourse(course) > 0
                ? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(course, "Course's inserted",
                ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(course, "Course's already existed",
                ResponseObject.Status.STATUS_FAILED));
    }

    @PutMapping("/updateCourse")
    ResponseEntity<ResponseObject> updateCourse(@RequestBody Course course) {
        return courseService.updateCourse(course) > 0
                ? ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(course,
                "Course's updated", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(course, "Course's not existed",
                ResponseObject.Status.STATUS_FAILED));
    }

    @DeleteMapping("deleteCourseById/{id}")
    ResponseEntity<ResponseObject> deleteCourse(@PathVariable int id) {
        courseService.deleteCourse(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject("", "Course's deleted", ResponseObject.Status.STATUS_OK));
    }
}