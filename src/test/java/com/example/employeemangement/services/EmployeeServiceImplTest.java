package com.example.employeemangement.services;

import com.example.employeemangement.api.v1.mapper.EmployeeMapper;
import com.example.employeemangement.api.v1.model.EmployeeDto;
import com.example.employeemangement.domains.Employee;
import com.example.employeemangement.exception.EmployeeNotFoundException;
import com.example.employeemangement.repository.EmployeeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {
    public static final long EMP_ID = 1L;
    public static final String FIRST_NAME = "first";
    public static final String LAST_NAME = "last";
    EmployeeService employeeService;

    EmployeeMapper employeeMapper;

    @Mock
    EmployeeRepository employeeRepository;

    @Captor
    ArgumentCaptor<Long> employeeIdCaptor;


    @BeforeEach
    void setUp() {
        employeeMapper = EmployeeMapper.INSTANCE;
        employeeService = new EmployeeServiceImpl(employeeRepository,employeeMapper);
    }

    @Test
    void getEmployeeByIdShouldThrowsEmployeeNotFoundExp(){
//        Given
        given(employeeRepository.findById(any())).willReturn(Optional.empty());

//        Then
        assertThrows(EmployeeNotFoundException.class,()->employeeService.getEmployeeById(EMP_ID));

    }

    @Test
    void getEmployeeById() {
//        Given
        Employee employee = getEmployee();
        given(employeeRepository.findById(employeeIdCaptor.capture())).willReturn(Optional.of(employee));

//        When
       EmployeeDto employeeDto = employeeService.getEmployeeById(EMP_ID);

//       Then
        assertNotNull(employeeDto);
        assertEquals(EMP_ID,employeeDto.getId());
        assertEquals(FIRST_NAME,employeeDto.getFirstName());
        assertEquals(LAST_NAME,employeeDto.getLastName());

        assertEquals(EMP_ID,employeeIdCaptor.getValue());

        then(employeeRepository).should().findById(employeeIdCaptor.getValue());
        then(employeeRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void getAllEmployees(){
//        Given
        List<Employee> employees = new ArrayList<>();

        Employee employee = getEmployee();
        employees.add(employee);

        given(employeeRepository.findAll()).willReturn(employees);

//        When
      List<EmployeeDto> employeeDtoS=  employeeService.getAllEmployees();

//      Then
        assertNotNull(employeeDtoS);
        assertEquals(1,employeeDtoS.size());
        then(employeeRepository).should().findAll();
        then(employeeRepository).shouldHaveNoMoreInteractions();
    }

//    Private Methods
private Employee getEmployee() {
    Employee employee = new Employee();
    employee.setId(EMP_ID);
    employee.setFirstName(FIRST_NAME);
    employee.setLastName(LAST_NAME);
    return employee;
}
}