package com.rancard.springMon.service;

import com.rancard.springMon.exception.StudentNotFoundException;
import com.rancard.springMon.model.StudentModel;
import com.rancard.springMon.model.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public List<StudentModel> getAllStudents(){
        return studentRepository.findAll()  ;
    }
    public StudentModel createStudent(StudentModel studentModel){
        return studentRepository.save(studentModel);
    }
    public StudentModel getStudentByID(String studentID){
        StudentModel studentModel = studentRepository.findById(studentID).orElse(null);
        if (studentModel == null){
            throw new StudentNotFoundException("Student with ID " + studentID + " not found");
        }
        return studentModel;
    }
    public boolean deleteStudent(String studentID){
        if (studentRepository.existsById(studentID)){
            studentRepository.deleteById(studentID);
            return true;
        }
        return false;
    }
    public ResponseEntity<StudentModel> updateStudent(String studentId, StudentModel updatedStudent){
        try{
            StudentModel existingStudent = getStudentByID(studentId);
            if (updatedStudent.getFirstName() != null){
                existingStudent.setFirstName(updatedStudent.getFirstName());
            }
            // lastName
            if (updatedStudent.getLastName() != null){
                existingStudent.setLastName(updatedStudent.getLastName());
            }
            // Email
            if (updatedStudent.getEmail() != null){
                existingStudent.setEmail(updatedStudent.getEmail());
            }
            // Gender
            if (updatedStudent.getGender() != null){
                existingStudent.setGender(updatedStudent.getGender());
            }
            // Elective
            if (updatedStudent.getElectiveSubjects() != null){
                existingStudent.setElectiveSubjects(updatedStudent.getElectiveSubjects());
            }
            // amountSpent
            if (updatedStudent.getAmountSpent() != null){
                existingStudent.setAmountSpent(updatedStudent.getAmountSpent());
            }
            // address
            if (updatedStudent.getStudentAddress() != null){
                existingStudent.setStudentAddress(updatedStudent.getStudentAddress());
            }
            StudentModel savedStudent = studentRepository.save(existingStudent);
            return ResponseEntity.ok(savedStudent);
        } catch (StudentNotFoundException e){
            return ResponseEntity.notFound().build();
        }
    }
}
