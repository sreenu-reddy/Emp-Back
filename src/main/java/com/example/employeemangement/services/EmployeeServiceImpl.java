package com.example.employeemangement.services;

import com.example.employeemangement.api.v1.mapper.EmployeeMapper;
import com.example.employeemangement.api.v1.model.EmployeeDto;
import com.example.employeemangement.domains.Employee;
import com.example.employeemangement.exception.EmployeeNotFoundException;
import com.example.employeemangement.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

    @Override
    public List<EmployeeDto> getAllEmployees() {
      List<Employee> employees = employeeRepository.findAll();
       return employees.stream()
                .map(employeeMapper::employeeToEmployeeDto)
                .collect(Collectors.toList());
    }

    @Override
    public EmployeeDto createNewEmployee(EmployeeDto employee) {
        if (employee==null){
            throw new NullPointerException("EmployeeDto is null:");

        }else{
            Employee detachedEmployee = employeeMapper.employeeDtoToEmployee(employee);
            Employee savedEmployee = employeeRepository.save(detachedEmployee);
            return employeeMapper.employeeToEmployeeDto(savedEmployee);
        }

    }

    @Override
    public EmployeeDto updateEmployee(Long employeeId,EmployeeDto employeeDto) {
        if (employeeDto ==null){
            throw new NullPointerException("EmployeeDto is null:");
        }else{
            Employee detachedEmployee = employeeMapper.employeeDtoToEmployee(employeeDto);
            detachedEmployee.setId(employeeId);
            Employee savedEmployee = employeeRepository.save(detachedEmployee);
            return employeeMapper.employeeToEmployeeDto(savedEmployee);
        }
    }


}
