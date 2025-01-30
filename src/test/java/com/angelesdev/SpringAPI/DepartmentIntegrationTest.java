package com.angelesdev.SpringAPI;

import com.angelesdev.SpringAPI.model.Department;
import com.angelesdev.SpringAPI.repository.DepartmentRepository;
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
public class DepartmentIntegrationTest {

    private static final String DEPT_NO = "d999";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Test
    @Transactional
    public void testCreateDepartment() throws Exception {

        // CREATE DEPARTMENT OBJECT
        Department newDepartment = new Department();
        newDepartment.setDeptNo(DEPT_NO);
        newDepartment.setDeptName("Digital Services");

        // departmentRepository.save(newDepartment);
               
        // GENERATE JSON
        String newDepartmentJson = objectMapper.writeValueAsString(newDepartment);

        // POST REQUEST TO CREATE NEW DEPARTMENT
        mockMvc.perform(post("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newDepartmentJson))
                .andExpect(status().isCreated());  // 201 Created

        // VERIFY CREATED DEPARTMENT
        Department createdDepartment = departmentRepository.findById(DEPT_NO).orElseThrow();
        assertEquals(DEPT_NO, createdDepartment.getDeptNo());

        // MODIFY DEPARTMENT DATA
        newDepartment.setDeptName("Security");

        // GENERATE NEW JSON
        newDepartmentJson = objectMapper.writeValueAsString(newDepartment);

        // PUT REQUEST TO UPDATE DEPARTMENT
        mockMvc.perform(put("/departments")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newDepartmentJson))
                .andExpect(status().isOk()); // 200 OK

        // DELETE REQUEST TO DELETE DEPARTMENT
        mockMvc.perform(delete("/departments/" + DEPT_NO))
                .andExpect(status().isNoContent()); // 204 No Content

        // VERIFY DELETED DEPARTMENT
        assertFalse(departmentRepository.findById(DEPT_NO).isPresent(), "Department deleted.");
    }
}
