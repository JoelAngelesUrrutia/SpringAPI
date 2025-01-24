package com.angelesdev.SpringAPI.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.angelesdev.SpringAPI.model.Department;
import com.angelesdev.SpringAPI.repository.DepartmentRepository;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    // Get all departments
    public List<Department> getAlldepartments(){
        return departmentRepository.findAll();
    }

    // Get department by id
    public Optional<Department> getDepartmentById(String deptNo) {
        return departmentRepository.findById(deptNo);
    }

    // Save or update a department
    public Department saveDepartment(Department department){
        return departmentRepository.save(department);
    }

    // Delete a department
    public void deleteDepartment(String dept_no){
        departmentRepository.deleteById(dept_no);
    }
}
