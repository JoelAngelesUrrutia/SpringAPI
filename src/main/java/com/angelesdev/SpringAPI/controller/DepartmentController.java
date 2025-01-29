package com.angelesdev.SpringAPI.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.angelesdev.SpringAPI.service.DepartmentService;
import com.angelesdev.SpringAPI.model.Department;


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    // Get all departments
    @GetMapping
    public List<Department> getAllDepartments(){
        return departmentService.getAlldepartments();
    }

    // Get department by id
    @GetMapping("/{dept_no}")
    public Optional<Department> getDepartmentById(@PathVariable String dept_no){
        return departmentService.getDepartmentById(dept_no);
    }

    // Save or update a department
    @PostMapping
    public Department saveDepartment(Department department){
        return departmentService.saveDepartment(department);
    }

    // Delete a department by id
    @DeleteMapping("/{dept_no}")
    public void deleteDepartment(@PathVariable String dept_no){
        departmentService.deleteDepartment(dept_no);
    }
}
