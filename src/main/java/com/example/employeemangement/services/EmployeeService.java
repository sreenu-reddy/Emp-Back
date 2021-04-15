package com.example.employeemangement.services;

import com.example.employeemangement.api.v1.model.EmployeeDto;

import java.util.List;

public interface EmployeeService {

    EmployeeDto getEmployeeById(Long id);
    List<EmployeeDto> getAllEmployees();
    EmployeeDto createNewEmployee(EmployeeDto employee);
    EmployeeDto updateEmployee(Long employeeId, EmployeeDto employeeDto);
    void deleteEmployeeById(Long id);
}
