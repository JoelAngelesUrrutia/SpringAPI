package com.angelesdev.SpringAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import com.angelesdev.SpringAPI.service.EmployeeService;
import com.angelesdev.SpringAPI.model.Employee;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    // Get all employees
    @Operation(summary = "Get all employees", description = "Return a list of all employees.")
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get employee by ID
    @Operation(summary = "Get employee by ID", description = "Return an employee info by ID.")
    @GetMapping("/{empNo}")
    public Optional<Employee> getEmployeeById(@Parameter(description = "employee ID") @PathVariable Long empNo) {
        return employeeService.getEmployeeById(empNo);
    }

    // Save an employee
    @Operation(summary = "Create an employee", description = "Creates an employee to database.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    // Update an employee
    @Operation(summary = "Update an employee", description = "Updates an employee to database.")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Employee updateEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    // Delete employee
    @Operation(summary = "Delete an employee by ID", description = "Deletes an employee by ID.")
    @DeleteMapping("/{empNo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteEmployee(@Parameter(description = "employee ID") @PathVariable Long empNo) {
        employeeService.deleteEmployee(empNo);
    }
}

