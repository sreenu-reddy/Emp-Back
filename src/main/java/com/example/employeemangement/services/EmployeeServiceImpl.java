package com.example.employeemangement.services;

import com.example.employeemangement.api.v1.mapper.EmployeeMapper;
import com.example.employeemangement.api.v1.model.EmployeeDto;
import com.example.employeemangement.domains.Employee;
import com.example.employeemangement.exception.EmployeeNotFoundException;
import com.example.employeemangement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final EmployeeMapper employeeMapper;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository, EmployeeMapper employeeMapper) {
        this.employeeRepository = employeeRepository;
        this.employeeMapper = employeeMapper;
    }

    @Override
    public EmployeeDto getEmployeeById(Long id) {
      Optional<Employee> employeeOptional=  employeeRepository.findById(id);
      if (employeeOptional.isEmpty()){
          throw new EmployeeNotFoundException("Employee is not found with given ID: "+id);
      }else {
         Employee  employee= employeeOptional.get();
         return employeeMapper.employeeToEmployeeDto(employee);
      }
    }
}
