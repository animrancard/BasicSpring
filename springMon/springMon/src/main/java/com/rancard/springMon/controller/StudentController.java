package com.rancard.springMon.controller;

import com.rancard.springMon.exception.StudentNotFoundException;
import com.rancard.springMon.model.StudentModel;
import com.rancard.springMon.service.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final StudentService studentService;
    @GetMapping // Get all students
    public List<StudentModel> allStudents(){
        return studentService.getAllStudents();
    }
    @GetMapping("/{studentId}") // Get single student
    public ResponseEntity<StudentModel> getSingleStudent(@PathVariable String studentId) {
        try {
            StudentModel student = studentService.getStudentByID(studentId);
            return ResponseEntity.ok(student);
        } catch (StudentNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete Student
    @DeleteMapping("/{studentId}") // delete student
    public ResponseEntity<?> deleteSingleStudent(@PathVariable String studentId){
        if (studentService.deleteStudent(studentId)){
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
    @PostMapping // Create student
    public ResponseEntity<StudentModel> createStudent(@RequestBody StudentModel student){
        StudentModel savedStudent = studentService.createStudent(student);
        return new ResponseEntity<>(savedStudent, HttpStatus.CREATED);
    }

    @PutMapping("/{studentID}") // put student
    public ResponseEntity<StudentModel> putStudent(@PathVariable String studentID, @RequestBody StudentModel updatedStudent){
        ResponseEntity<StudentModel> response = studentService.updateStudent(studentID, updatedStudent);
        if (response.getStatusCode() == HttpStatus.OK){
            return response;
        }
        return ResponseEntity.notFound().build();

//        Optional<StudentModel> existingStudent = studentService.getStudentByID(studentID);
//        if (existingStudent.isPresent()){
//            StudentModel updated = existingStudent.get();
//            updated.setFirstName(updatedStudent.getFirstName());
//            updated.setLastName(updatedStudent.getLastName());
//            updated.setStudentAddress(updatedStudent.getStudentAddress());
//            updated.setEmail(updatedStudent.getEmail());
//            updated.setGender(updatedStudent.getGender());
//            updated.setAmountSpent(updatedStudent.getAmountSpent());
//            updated.setElectiveSubjects(updatedStudent.getElectiveSubjects());
//
//            StudentModel savedStudent = studentService.updateStudent(updated);
//            return ResponseEntity.ok(savedStudent);
//        }
//        return ResponseEntity.notFound().build();
    }
}
