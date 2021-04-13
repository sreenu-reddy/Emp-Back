package com.example.employeemangement.api.v1.model;

import lombok.Data;

@Data
public class EmployeeDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String emailId;
}
