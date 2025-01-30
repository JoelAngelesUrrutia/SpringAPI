package com.angelesdev.SpringAPI;

import com.angelesdev.SpringAPI.model.Employee;
import com.angelesdev.SpringAPI.repository.EmployeeRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeIntegrationTest {

    private static final long EMP_NO = 102L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Test
    @Transactional
    public void testCreateEmployee() throws Exception {

        // CREATE EMPLOYEE OBJECT
        Employee newEmployee = new Employee();
        newEmployee.setEmpNo(EMP_NO);
        newEmployee.setFirstName("John");
        newEmployee.setLastName("Doe");
        newEmployee.setBirthDate("1990-01-01");
        newEmployee.setHireDate("2020-01-01");

        // GENERATE JSON
        String newEmployeeJson = objectMapper.writeValueAsString(newEmployee);

        // POST REQUEST TO CREATE NEW EMPLOYEE
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEmployeeJson))
                .andExpect(status().isCreated());  // 201 Created

        // VERIFY CREATED EMPLOYEE
        Employee createdEmployee = employeeRepository.findById(EMP_NO).orElseThrow();
        assertEquals(EMP_NO, createdEmployee.getEmpNo());

        // MODIFY EMPLOYEE DATA
        newEmployee.setFirstName("Sam");
        newEmployee.setLastName("Cooper");
        newEmployee.setBirthDate("1999-01-01");
        newEmployee.setHireDate("2019-01-01");

        // GENERATE NEW JSON
        newEmployeeJson = objectMapper.writeValueAsString(newEmployee);

        // PUT REQUEST TO UPDATE EMPLOYEE
        mockMvc.perform(put("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEmployeeJson))
                .andExpect(status().isOk()); // 200 OK

        // DELETE REQUEST TO DELETE EMPLOYEE
        mockMvc.perform(delete("/employees/" + EMP_NO))
                .andExpect(status().isNoContent()); // 204 No Content

        // VERIFY DELETED EMPLOYEE
        assertFalse(employeeRepository.findById(EMP_NO).isPresent(), "Employee deleted.");
    }
}
