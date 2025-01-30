package com.angelesdev.SpringAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.angelesdev.SpringAPI.service.DepartmentService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;

import com.angelesdev.SpringAPI.model.Department;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Get all departments
    @Operation(summary = "Get all departments", description = "Return a list of all departments.")
    @GetMapping
    public List<Department> getAllDepartments(){
        return departmentService.getAlldepartments();
    }

    // Get department by id
    @Operation(summary = "Get department by ID", description = "Return a department info by ID.")
    @GetMapping("/{dept_no}")
    public Optional<Department> getDepartmentById(@Parameter(description = "department ID")@PathVariable String dept_no){
        return departmentService.getDepartmentById(dept_no);
    }

    // Create a department
    @Operation(summary = "Create a department", description = "Creates a department to database.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Department saveDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    // Update a department
    @Operation(summary = "Update a department", description = "Updates a department to database.")
    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public Department updateDepartment(@RequestBody Department department){
        return departmentService.saveDepartment(department);
    }

    // Delete a department by id
    @Operation(summary = "Delete a department by ID", description = "Deletes a department by ID.")
    @DeleteMapping("/{dept_no}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteDepartment(@Parameter(description = "department ID") @PathVariable String dept_no){
        departmentService.deleteDepartment(dept_no);
    }
}
