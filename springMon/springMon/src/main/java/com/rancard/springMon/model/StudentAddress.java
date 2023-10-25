package com.rancard.springMon.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentAddress {
    private String country;
    private String postCode;
    private String city;
}
