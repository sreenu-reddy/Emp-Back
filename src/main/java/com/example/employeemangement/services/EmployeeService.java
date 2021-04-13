package com.example.employeemangement.services;

import com.example.employeemangement.api.v1.model.EmployeeDto;

public interface EmployeeService {

    EmployeeDto getEmployeeById(Long id);
}
