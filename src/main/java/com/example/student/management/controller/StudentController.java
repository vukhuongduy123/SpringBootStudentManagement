package com.example.student.management.controller;

import com.example.student.management.models.ResponseObject;
import com.example.student.management.models.Student;
import com.example.student.management.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/student")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping("/getAllStudents")
    public ResponseEntity<ResponseObject> getAllStudents() {
        List<Student> students = studentService.getAllStudents();
        return students.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(students, "There aren't any students", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(students, "List of students", ResponseObject.Status.STATUS_OK));
    }

    @GetMapping("/getStudentById/{id}")
    ResponseEntity<ResponseObject> getStudentFromId(@PathVariable int id) {
        Optional<Student> student = studentService.getStudentFromId(id);

        return student.isPresent() ? ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject(student, "Student found", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Student not found", ResponseObject.Status.STATUS_FAILED));
    }

    @GetMapping("/getStudentByName/{name}")
    ResponseEntity<ResponseObject> getStudentByName(@PathVariable String name) {
        List<Student> students = studentService.getStudentByName(name);

        return students.size() > 0 ? ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject(students, "Students found", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Students not found", ResponseObject.Status.STATUS_FAILED));
    }

    @GetMapping("/getStudentByDepartmentId/{id}")
    ResponseEntity<ResponseObject> getStudentByDepartmentId(@PathVariable int id) {
        List<Student> students = studentService.getStudentByDepartmentId(id);

        return students.size() > 0 ? ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject(students, "Students found", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Students not found", ResponseObject.Status.STATUS_FAILED));
    }

    @GetMapping("/countAllStudents")
    ResponseEntity<ResponseObject> countAllStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(studentService.countAllStudents(),
                "Number of students", ResponseObject.Status.STATUS_OK));
    }

    @GetMapping("/countStudentsFromDeparted/{id}")
    public ResponseEntity<ResponseObject> countStudentsFromDeparted(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(studentService.countStudentsFromDeparted(id),
                "Number of students", ResponseObject.Status.STATUS_OK));
    }

    @PostMapping("/insertStudent")
    ResponseEntity<ResponseObject> insertStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(studentService.insertStudent(student),
                "Student's inserted", ResponseObject.Status.STATUS_OK));
    }

    @PutMapping("/updateStudent")
    ResponseEntity<ResponseObject> updateStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(studentService.updateStudent(student),
                "Student's updated", ResponseObject.Status.STATUS_OK));
    }

    @DeleteMapping("deleteStudentById/{id}")
    ResponseEntity<ResponseObject> deleteStudent(@PathVariable int id) {
        studentService.deleteStudent(id);
        return ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject("", "Student's deleted", ResponseObject.Status.STATUS_OK));
    }
}
