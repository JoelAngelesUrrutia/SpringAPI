package com.angelesdev.SpringAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;


@Entity
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "dept_no", length = 4)
    private String empNo;

    @Column(name = "dept_name")
    private String deptName;

    public String getEmpNo(){
        return empNo;
    }

    public void setEmpNo(String empNo){
        this.empNo = empNo;
    }

    public String getDeptName(){
        return deptName;
    }

    public void setDeptName(String deptName){
        this.deptName = deptName;
    }
}
