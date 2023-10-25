package com.rancard.springMon.service;

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
    public Optional<StudentModel> getStudentByID(String studentID){
        return studentRepository.findById(studentID);
    }
    public boolean deleteStudent(String studentID){
        if (studentRepository.existsById(studentID)){
            studentRepository.deleteById(studentID);
            return true;
        }
        return false;
    }
    public ResponseEntity<StudentModel> updateStudent(String studentID, StudentModel updatedStudent){
        Optional<StudentModel> existingStudent = getStudentByID(studentID);

        if (existingStudent.isPresent()){
            StudentModel existing = existingStudent.get();
            // firstName
            if (updatedStudent.getFirstName() != null){
                existing.setFirstName(updatedStudent.getFirstName());
            }
            // lastName
            if (updatedStudent.getLastName() != null){
                existing.setLastName(updatedStudent.getLastName());
            }
            // Email
            if (updatedStudent.getEmail() != null){
                existing.setEmail(updatedStudent.getEmail());
            }
            // Gender
            if (updatedStudent.getGender() != null){
                existing.setGender(updatedStudent.getGender());
            }
            // Elective
            if (updatedStudent.getElectiveSubjects() != null){
                existing.setElectiveSubjects(updatedStudent.getElectiveSubjects());
            }
            // amountSpent
            if (updatedStudent.getAmountSpent() != null){
                existing.setAmountSpent(updatedStudent.getAmountSpent());
            }
            // address
            if (updatedStudent.getStudentAddress() != null){
                existing.setStudentAddress(updatedStudent.getStudentAddress());
            }
            StudentModel savedStudent = studentRepository.save(existing);
            return ResponseEntity.ok(savedStudent);
        }
        return ResponseEntity.notFound().build();
    }
}
