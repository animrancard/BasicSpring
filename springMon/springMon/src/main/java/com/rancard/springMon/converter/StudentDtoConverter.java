package com.rancard.springMon.converter;

import com.rancard.springMon.model.StudentModel;
import com.rancard.springMon.model.response.CreateStudentResponseDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentDtoConverter {
    @Autowired
    private ModelMapper modelMapper;
    public CreateStudentResponseDto convertStudentToResponseDto(StudentModel studentModel){
        return modelMapper.map(studentModel, CreateStudentResponseDto.class);

    }
    public StudentModel convertResponseDtoToStudent(CreateStudentResponseDto createStudentResponseDto){
        return modelMapper.map(createStudentResponseDto, StudentModel.class);
    }
}
