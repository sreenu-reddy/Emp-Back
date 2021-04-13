package com.example.employeemangement.bootstrap;

import com.example.employeemangement.domains.Employee;
import com.example.employeemangement.repository.EmployeeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class EmployeeBootStrap implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public EmployeeBootStrap(EmployeeRepository employeeRepository) {
        log.debug("BootStrap Data is being loaded....");
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args){
        Employee employee = new Employee();
        employee.setFirstName("Ram");
        employee.setLastName("AAA");
        employee.setEmailId("ram.aaa@gmail.com");

        Employee employee1 = new Employee();
        employee1.setFirstName("Sam");
        employee1.setLastName("BBB");
        employee1.setEmailId("sam.bbb@gmail.com");

        Employee employee2 = new Employee();
        employee2.setFirstName("Som");
        employee2.setLastName("CCC");
        employee2.setEmailId("som.ccc@gmail.com");

        employeeRepository.save(employee);
        employeeRepository.save(employee1);
        employeeRepository.save(employee2);

        log.debug("Loaded Employees records: "+employeeRepository.count());

    }
}
