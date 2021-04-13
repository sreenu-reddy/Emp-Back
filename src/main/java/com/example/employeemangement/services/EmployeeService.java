package com.example.employeemangement.services;

import com.example.employeemangement.api.v1.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto getEmployeeById(Long id);
    List<EmployeeDto> getAllEmployees();
}
