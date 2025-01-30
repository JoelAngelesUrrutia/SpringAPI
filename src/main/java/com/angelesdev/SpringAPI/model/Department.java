package com.angelesdev.SpringAPI.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Table;

@Entity
@Table(name = "departments")
public class Department {

    @Id
    @Column(name = "dept_no", length = 4)
    @JsonProperty("deptNo")
    private String deptNo;

    @Column(name = "dept_name")
    @JsonProperty("deptName")
    private String deptName;

    public String getDeptNo(){
        return deptNo;
    }

    public void setDeptNo(String deptNo){
        this.deptNo = deptNo;
    }

    public String getDeptName(){
        return deptName;
    }

    public void setDeptName(String deptName){
        this.deptName = deptName;
    }
}
