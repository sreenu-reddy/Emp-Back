package com.example.employeemangement.api.v1.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
public class EmployeeListDto {
    List<EmployeeDto> employees;
}
