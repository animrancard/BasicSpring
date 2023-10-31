package com.rancard.springMon.service;

import com.rancard.springMon.converter.StudentDtoConverter;
import com.rancard.springMon.exception.StudentNotFoundException;
import com.rancard.springMon.model.StudentModel;
import com.rancard.springMon.model.StudentRepository;
import com.rancard.springMon.model.request.CreateStudentRequestDto;
import com.rancard.springMon.model.response.CreateStudentResponseDto;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


@AllArgsConstructor
@Service
public class StudentService {
    @Autowired
    StudentDtoConverter studentDtoConverter;
    private final Logger log = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    public List<StudentModel> getAllStudents(){
        return studentRepository.findAll()  ;
    }
    public CreateStudentResponseDto createStudent(CreateStudentRequestDto student, String session) {
        log.info("{} creating {}", session, student);
        StudentModel savedStudent = StudentModel.builder()
                .firstName(student.getFirstName())
                .lastName(student.getLastName())
                .email(student.getEmail())
                .gender(student.getGender())
                .studentAddress(student.getStudentAddress())
                .electiveSubjects(student.getElectiveSubjects())
                .amountSpent(student.getAmountSpent())
                .build();
        log.info("{} creating DB with data {}", session, student);
        StudentModel saved = studentRepository.save(savedStudent);
        return studentDtoConverter.convertStudentToResponseDto(saved);
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
