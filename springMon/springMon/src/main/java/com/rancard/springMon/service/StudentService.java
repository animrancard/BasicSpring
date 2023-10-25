package com.rancard.springMon.service;

import com.rancard.springMon.model.StudentModel;
import com.rancard.springMon.model.StudentRepository;
import lombok.AllArgsConstructor;
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
}
