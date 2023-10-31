package com.rancard.springMon.model.request;

import com.rancard.springMon.model.StudentAddress;
import com.rancard.springMon.model.StudentGender;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CreateStudentRequestDto implements Serializable {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private StudentGender gender;
    private StudentAddress studentAddress;
    private List<String> electiveSubjects;
    private BigDecimal amountSpent;
}
