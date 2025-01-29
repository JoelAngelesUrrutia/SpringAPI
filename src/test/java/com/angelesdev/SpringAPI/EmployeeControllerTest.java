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
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EmployeeControllerTest {

    private static final long EMP_NO = 102L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;  // Usado para convertir objetos a JSON

    @Autowired
    private EmployeeRepository employeeRepository;  // Acceso a la base de datos

    @Test
    @Transactional  // Asegura que los cambios se revierten después de la prueba
    public void testCreateEmployee() throws Exception {
        // Crear un objeto Employee
        Employee newEmployee = new Employee();
        newEmployee.setEmpNo(EMP_NO);
        newEmployee.setFirstName("John");
        newEmployee.setLastName("Doe");
        newEmployee.setBirthDate("1990-01-01");
        newEmployee.setHireDate("2020-01-01");

        // Convertir el objeto Employee a JSON
        String newEmployeeJson = objectMapper.writeValueAsString(newEmployee);

        // Realizar la solicitud POST para crear el nuevo empleado
        mockMvc.perform(post("/employees")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newEmployeeJson)) // Usar el JSON generado
                .andExpect(status().isCreated());  // Esperar respuesta 201 Created

        // Verificar que el empleado fue creado
        Employee createdEmployee = employeeRepository.findById(EMP_NO).orElseThrow();
        assertEquals(EMP_NO, createdEmployee.getEmpNo());

        // Eliminar el empleado usando DELETE
        mockMvc.perform(delete("/employees/" + EMP_NO))
                .andExpect(status().isNoContent()); // 204 No Content

        // Verificar que el empleado fue eliminado
        assertFalse(employeeRepository.findById(EMP_NO).isPresent(), "El empleado debería haber sido eliminado");
    }
}
