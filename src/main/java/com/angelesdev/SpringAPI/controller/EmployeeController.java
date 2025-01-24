package com.angelesdev.SpringAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    // Get employee by ID
    @GetMapping("/{empNo}")
    public Optional<Employee> getEmployeeById(@PathVariable Long empNo) {
        return employeeService.getEmployeeById(empNo);
    }

    // Save or update an employee
    @PostMapping
    public Employee saveEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }

    // Delete employee
    @DeleteMapping("/{empNo}")
    public void deleteEmployee(@PathVariable Long empNo) {
        employeeService.deleteEmployee(empNo);
    }
}

