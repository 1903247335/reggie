package com.example.fontend.service;

import com.example.fontend.entity.Employee;
import com.example.fontend.entity.Page;
import com.example.fontend.entity.PageResult;
import com.example.fontend.mapper.EmployeeMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public Employee getEmployee(Employee employee){return employeeMapper.getEmployee(employee);}
    public void addEmployee(Employee employee){
        employeeMapper.addEmployee(employee);
    }
    public void updateEmployee(Employee employee){
        employeeMapper.updateEmployee(employee);
    }



    public PageResult getEmployeesList(int currentPage,int pageSize,String name) {
        int pageCount= employeeMapper.getAllPageSize();
        int currentIndex=(currentPage-1)*pageSize;

        List<Employee>employeeList=employeeMapper.getEmployeeList(currentIndex,pageSize,name);
        log.info("{}",employeeList);
        PageResult pageResult=new PageResult();
        pageResult.setToTal(pageCount);
        pageResult.setEmployeeList(employeeList);
        return pageResult;
    }

    public void changeEmployee(Employee employee) {
        employeeMapper.changeEmployee(employee);
    }
}
