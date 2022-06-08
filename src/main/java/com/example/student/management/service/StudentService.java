package com.example.student.management.service;

import com.example.student.management.models.Student;
import com.example.student.management.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentFromId(int id) {
        return studentRepository.findById(id);
    }

    public List<Student> getStudentByName(String name) {
        return studentRepository.findByName(name);
    }

    public List<Student> getStudentByDepartmentId(int id) {
        return studentRepository.findByDepartmentId(id);
    }

    public Map<Integer,String> countAllStudents(){
        return studentRepository.countAll();
    }

    @Transactional(readOnly = true)
    public List<Object> countStudentsFromDeparted( int id){
        return studentRepository.CountStudentsFromDeparted(id);
    }

    public int insertStudent(Student student) {
        return studentRepository.insert(
                student.getId(), student.getName(), student.isGender(), student.getDob(), student.getDepartmentId());
    }

    public int updateStudent(Student student) {
        return studentRepository.update(
                student.getId(), student.getName(), student.isGender(), student.getDob(), student.getDepartmentId());
    }

    public void deleteStudent(int id) {
        studentRepository.deleteById(id);
    }
}
