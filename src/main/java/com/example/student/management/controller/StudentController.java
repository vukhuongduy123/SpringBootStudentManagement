package com.example.student.management.controller;

import com.example.student.management.models.ResponseObject;
import com.example.student.management.models.Student;
import com.example.student.management.repositories.StudentRepository;
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
    private StudentRepository studentRepository;

    @GetMapping("/getAllStudents")
    ResponseEntity<ResponseObject> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        return students.isEmpty() ? ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(students, "There aren't any students", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject(students, "List of students", ResponseObject.Status.STATUS_OK));
    }

    @GetMapping("/getStudentById/{id}")
    ResponseEntity<ResponseObject> getStudentFromId(@PathVariable int id) {
        Optional<Student> student = studentRepository.findById(id);

        return student.isPresent() ? ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject(student, "Student found", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Student not found", ResponseObject.Status.STATUS_FAILED));
    }

    @GetMapping("/getStudentByName/{name}")
    ResponseEntity<ResponseObject> getStudentByName(@PathVariable String name) {
        List<Student> students = studentRepository.findByName(name);

        return students.size() > 0 ? ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject(students, "Students found", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Students not found", ResponseObject.Status.STATUS_FAILED));
    }

    @GetMapping("/getStudentByDepartmentId/{id}")
    ResponseEntity<ResponseObject> getStudentByDepartmentId(@PathVariable int id) {
        List<Student> students = studentRepository.findByDepartmentId(id);

        return students.size() > 0 ? ResponseEntity.status(HttpStatus.FOUND).body(
                new ResponseObject(students, "Students found", ResponseObject.Status.STATUS_OK))
                : ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ResponseObject("", "Students not found", ResponseObject.Status.STATUS_FAILED));
    }

    @GetMapping("/countAllStudents")
    ResponseEntity<ResponseObject> countAllStudents(){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(studentRepository.countAll(),
                "Number of students", ResponseObject.Status.STATUS_OK));
    }

    @GetMapping("/countStudentsFromDeparted/{id}")
    @Transactional(readOnly = true)
    public ResponseEntity<ResponseObject> countStudentsFromDeparted(@PathVariable int id){
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(studentRepository.CountStudentsFromDeparted(id),
                "Number of students", ResponseObject.Status.STATUS_OK));
    }

    @PostMapping("/insertStudent")
    ResponseEntity<ResponseObject> insertStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(studentRepository.insert(
                student.getId(), student.getName(), student.isGender(), student.getDob(), student.getDepartmentId()),
                "Student's inserted", ResponseObject.Status.STATUS_OK));
    }

    @PutMapping("/updateStudent")
    ResponseEntity<ResponseObject> updateStudent(@RequestBody Student student) {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(studentRepository.update(
                student.getId(), student.getName(), student.isGender(), student.getDob(), student.getDepartmentId()),
                "Student's updated", ResponseObject.Status.STATUS_OK));

    }

    @DeleteMapping("deleteStudentById/{id}")
    ResponseEntity<ResponseObject> deleteStudent(@PathVariable int id) {
        //if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.FOUND).body(
                    new ResponseObject("", "Student's deleted", ResponseObject.Status.STATUS_OK));
        //}
       // return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
               // new ResponseObject("", "Student's not exist", ResponseObject.Status.STATUS_FAILED));
    }
}
