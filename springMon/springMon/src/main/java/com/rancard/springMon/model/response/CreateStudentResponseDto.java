package com.rancard.springMon.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.rancard.springMon.model.StudentGender;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CreateStudentResponseDto implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private StudentGender gender;
}
