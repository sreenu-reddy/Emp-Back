package com.example.employeemangement.controller.v1;

import com.example.employeemangement.api.v1.model.EmployeeDto;
import com.example.employeemangement.api.v1.model.EmployeeListDto;
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
    @GetMapping("/employees/{id}")
    public EmployeeDto getEmployeeById(@PathVariable Long id){
        try {
            return employeeService.getEmployeeById(id);
        }catch (Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,e.getMessage());
        }
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/employees")
    public EmployeeListDto getAllEmployees(){
        return new EmployeeListDto(employeeService.getAllEmployees());
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/employees")
    public EmployeeDto createNewEmployee(@RequestBody EmployeeDto employeeDto){
        return employeeService.createNewEmployee(employeeDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/employees/{id}")
    public EmployeeDto updateEmployee(@PathVariable Long id,@RequestBody EmployeeDto employeeDto){
        return employeeService.updateEmployee(id,employeeDto);
    }
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/employees/{id}")
    public void deleteEmployeeById(@PathVariable Long id){
        employeeService.deleteEmployeeById(id);
    }
}
