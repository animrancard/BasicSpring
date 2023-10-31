package com.rancard.springMon.controller;

import com.rancard.springMon.exception.StudentNotFoundException;
import com.rancard.springMon.model.StudentModel;
import com.rancard.springMon.model.request.CreateStudentRequestDto;
import com.rancard.springMon.model.response.CreateStudentResponseDto;
import com.rancard.springMon.service.StudentService;

import lombok.RequiredArgsConstructor;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import org.slf4j.Logger;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/v1/students")
@RequiredArgsConstructor
public class StudentController {
    private final Logger log = LoggerFactory.getLogger(StudentController.class);
    private final StudentService studentService;
    @GetMapping // Get all students
    public List<StudentModel> allStudents(HttpSession session){
        String sessionId = session.getId();
        log.info("{} made a request to get all students", sessionId);
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
    public CreateStudentResponseDto createStudent(@RequestBody CreateStudentRequestDto createStudentRequestDto, HttpSession session){
        String sessionId = session.getId();
        log.info("{} made a post request to create {}", sessionId, createStudentRequestDto);
        return studentService.createStudent(createStudentRequestDto, sessionId);
    }

    @PutMapping("/{studentID}") // put student
    public ResponseEntity<StudentModel> putStudent(@PathVariable String studentID, @RequestBody StudentModel updatedStudent){
        ResponseEntity<StudentModel> response = studentService.updateStudent(studentID, updatedStudent);
        if (response.getStatusCode() == HttpStatus.OK){
            return response;
        }
        return ResponseEntity.notFound().build();
    }
}
