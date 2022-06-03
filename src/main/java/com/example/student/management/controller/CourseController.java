package com.example.student.management.controller;

import com.example.student.management.models.Course;
import com.example.student.management.models.ResponseObject;
import com.example.student.management.repositories.CourseRepository;
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
    private CourseRepository courseRepository;

    @GetMapping("/getAllCourses")
    ResponseEntity<ResponseObject> getAllCourses() {
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(courseRepository.findAll(), "List of courses", ResponseObject.Status.STATUS_OK));
    }

    @GetMapping("/getCourseById/{id}")
    ResponseEntity<ResponseObject> getCourseById(@PathVariable int id) {
        Optional<Course> course = courseRepository.findById(id);
        return course.isPresent() ? ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject(course, "Course found", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Course not found", ResponseObject.Status.STATUS_FAILED));
    }

    @GetMapping("/getCoursesByName/{name}")
    ResponseEntity<ResponseObject> getCoursesByName(@PathVariable String name) {
        List<Course> courses = courseRepository.findByName(name);

        return courses.size() > 0 ? ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject(courses, "Courses found", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Courses not found", ResponseObject.Status.STATUS_FAILED));
    }

    @GetMapping("/countAllCourses")
    ResponseEntity<ResponseObject> countAllCourses(){
        int numberOfCourses = courseRepository.countAllId();
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(numberOfCourses, "Number of courses", ResponseObject.Status.STATUS_OK));
    }

    @PostMapping("/insertCourse")
    ResponseEntity<ResponseObject> insertCourse(@RequestBody Course course) {
        return getCourseById(course.getId()).getBody().getStatus() == ResponseObject.Status.STATUS_FAILED ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(courseRepository.save(course), "Course's inserted",
                                ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(course, "Course's already existed",
                        ResponseObject.Status.STATUS_FAILED));
    }

    @PutMapping("/updateCourse")
    ResponseEntity<ResponseObject> updateCourse(@RequestBody Course course) {
        return getCourseById(course.getId()).getBody().getStatus() == ResponseObject.Status.STATUS_OK ?
                ResponseEntity.status(HttpStatus.OK).body(
                        new ResponseObject(courseRepository.save(course), "Course's updated",
                                ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(course, "Course's not existed",
                        ResponseObject.Status.STATUS_FAILED));
    }

    @DeleteMapping("deleteCourseById/{id}")
    ResponseEntity<ResponseObject> deleteCourse(@PathVariable int id) {
        if (courseRepository.existsById(id)) {
            courseRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new ResponseObject("", "Course's deleted", ResponseObject.Status.STATUS_OK));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Course's not exist", ResponseObject.Status.STATUS_FAILED));
    }

}
