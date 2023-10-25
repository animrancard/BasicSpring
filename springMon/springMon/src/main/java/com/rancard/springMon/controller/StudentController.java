package com.rancard.springMon.controller;

import com.rancard.springMon.model.StudentModel;
import com.rancard.springMon.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
@AllArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping
    public List<StudentModel> allStudents(){
        return studentService.getAllStudents();
    }
    @GetMapping("/{studentID}")
    public ResponseEntity<StudentModel> getSingleStudent(@PathVariable String studentID){
        Optional<StudentModel> student = studentService.getStudentByID(studentID);

        if (student.isPresent()){
            return ResponseEntity.ok(student.get());
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping
    public ResponseEntity<StudentModel> createStudent(@RequestBody StudentModel student){
        StudentModel savedStudent = studentService.createStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }
}
