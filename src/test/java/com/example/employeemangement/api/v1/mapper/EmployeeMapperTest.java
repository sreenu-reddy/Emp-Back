package com.example.employeemangement.api.v1.mapper;

import com.example.employeemangement.api.v1.model.EmployeeDto;
import com.example.employeemangement.domains.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeMapperTest {

    public static final long EMP_ID = 1L;
    public static final String FIRST_NAME = "first";
    public static final String LAST_NAME = "last";
    private EmployeeMapper employeeMapper;


    @BeforeEach
    void setUp() {
        employeeMapper = EmployeeMapper.INSTANCE;
    }

    @Test
    void employeeToEmployeeDto() {
//        Given
        Employee employee = new Employee();
        employee.setId(EMP_ID);
        employee.setFirstName(FIRST_NAME);
        employee.setLastName(LAST_NAME);

//        When
      EmployeeDto employeeDto = employeeMapper.employeeToEmployeeDto(employee);

//      Then
        assertNotNull(employeeDto);
        assertEquals(EMP_ID,employeeDto.getId());
        assertEquals(FIRST_NAME,employeeDto.getFirstName());
        assertEquals(LAST_NAME,employeeDto.getLastName());

    }


    @Test
    void employeeToEmployeeDtoReturnsNull(){
//        When
        EmployeeDto employeeDto = employeeMapper.employeeToEmployeeDto(null);

//        Then
        assertNull(employeeDto);
    }

    @Test
    void employeeToEmployeeDtoReturnsEmptyObj(){
//        When
        EmployeeDto  employeeDto = employeeMapper.employeeToEmployeeDto(new Employee());

//        Then
        assertNotNull(employeeDto);
    }

    @Test
    void employeeDtoToEmployeeReturnsEmptyObj(){
//        When
        Employee employee = employeeMapper.employeeDtoToEmployee(new EmployeeDto());
//        Then
        assertNotNull(employee);
    }

    @Test
    void employeeDtoToEmployeeReturnsNull(){
//        When
        Employee employee = employeeMapper.employeeDtoToEmployee(null);
//        Then
        assertNull(employee);
    }

    @Test
    void employeeDtoToEmployee(){
//        Given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(EMP_ID);
        employeeDto.setFirstName(FIRST_NAME);
        employeeDto.setLastName(LAST_NAME);

//        When
        Employee employee = employeeMapper.employeeDtoToEmployee(employeeDto);
//        Then
        assertNotNull(employee);
        assertEquals(EMP_ID,employee.getId());
        assertEquals(FIRST_NAME,employee.getFirstName());
        assertEquals(LAST_NAME,employee.getLastName());
    }


}