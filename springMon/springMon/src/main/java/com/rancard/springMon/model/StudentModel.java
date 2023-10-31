package com.rancard.springMon.model;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentModel {
    @Id
    private String id;
    private String firstName;
    private String lastName;
    @Indexed(unique = true)
    private String email;
    private StudentGender gender;
    private StudentAddress studentAddress;
    private List<String> electiveSubjects;
    private BigDecimal amountSpent;
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();
}
