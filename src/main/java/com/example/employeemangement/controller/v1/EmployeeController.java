package com.example.employeemangement.controller.v1;

import com.example.employeemangement.api.v1.model.EmployeeDto;
import com.example.employeemangement.exception.EmployeeNotFoundException;
import com.example.employeemangement.services.EmployeeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employee/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        try {
            return employeeService.getEmployeeById(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }

    }
}
