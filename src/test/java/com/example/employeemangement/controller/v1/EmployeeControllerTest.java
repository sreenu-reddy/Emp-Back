package com.example.employeemangement.controller.v1;

import com.example.employeemangement.api.v1.model.EmployeeDto;
import com.example.employeemangement.services.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.server.ResponseStatusException;




import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {
    public static final long EMP_ID = 1L;
    public static final String FIRST_NAME = "first";
    public static final String LAST_NAME = "last";

    @Mock
    EmployeeService employeeService;

    EmployeeController controller;

    MockMvc mockMvc;

    @Captor
    ArgumentCaptor<Long> employeeIdCaptor;

    @BeforeEach
    void setUp() {
        controller = new EmployeeController(employeeService);
        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    void getEmployeeById() {
//        Given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(EMP_ID);
        employeeDto.setLastName(LAST_NAME);
        employeeDto.setFirstName(FIRST_NAME);
        given(employeeService.getEmployeeById(employeeIdCaptor.capture())).willReturn(employeeDto);

//        When
        EmployeeDto employeeDto1 = controller.getEmployeeById(EMP_ID);

//        Then
        assertNotNull(employeeDto1);
        assertEquals(EMP_ID,employeeDto1.getId());
        assertEquals(FIRST_NAME,employeeDto1.getFirstName());
        assertEquals(LAST_NAME,employeeDto1.getLastName());

        assertEquals(EMP_ID,employeeIdCaptor.getValue());

        then(employeeService).should().getEmployeeById(employeeIdCaptor.getValue());
        then(employeeService).shouldHaveNoMoreInteractions();

    }

    @Test
    void getEmployeeByIdControllerStatusIs404() throws Exception {
//        Given
        given(employeeService.getEmployeeById(anyLong())).willThrow(ResponseStatusException.class);

//        Then
        mockMvc.perform(get("/api/v1/employee/9")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }


    @Test
    void getEmployeeByIdControllerStatusIsOk() throws Exception {
//        Given
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setId(EMP_ID);
        employeeDto.setLastName(LAST_NAME);
        employeeDto.setFirstName(FIRST_NAME);
        given(employeeService.getEmployeeById(anyLong())).willReturn(employeeDto);

//        Then
        mockMvc.perform(get("/api/v1/employee/1")
        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.lastName",equalTo(LAST_NAME)));
    }
}