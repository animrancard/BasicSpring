package com.rancard.springMon.service;

import com.rancard.springMon.model.StudentModel;
import com.rancard.springMon.model.StudentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
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
}