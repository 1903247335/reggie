package com.example.fontend.controller;

import com.example.fontend.common.PublicFill;
import com.example.fontend.entity.Page;
import com.example.fontend.entity.PageResult;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import com.example.fontend.common.result;
import com.example.fontend.entity.Employee;
import com.example.fontend.service.EmployeeService;
import org.apache.ibatis.annotations.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import com.example.fontend.untils.untils;

@RestController
@Slf4j
@RequestMapping(value = "/font-end")

public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    public result logout(HttpServletRequest request){
        request.getSession().removeAttribute("id");
        return result.success("退出成功");
    }
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public  result loginBackEnd(HttpServletRequest request, @RequestBody Employee employee){
            log.info("{}",employee);
            String password=employee.getPassword();
            password= DigestUtils.md5DigestAsHex(password.getBytes());
            String username=employee.getUsername();
            employee.setPassword(password);
            employee.setUsername(username);
            Employee emp=employeeService.getEmployee(employee);
            log.info("在这里{}",emp);
            if (emp==null){
                return result.error("登录失败");
            }else{
                String id=String.valueOf(emp.getId());
                request.getSession().setAttribute("id",id);

                return result.success(emp);
            }

    }
    @RequestMapping(value="/add-employee",method = RequestMethod.POST)
    public result addEmployees(HttpServletRequest request,@RequestBody Employee employee){

        String password=DigestUtils.md5DigestAsHex("123456".getBytes());
        employee.setId(untils.createdUUID());
        employee.setPassword(password);
        employee.setStatus(1);
        PublicFill.fill(request, employee);

        try {
            employeeService.addEmployee(employee);
            return result.success("添加成功");
        }
        catch (Exception e){
            return result.error("已经有重复用户");
        }



    }

    @RequestMapping(value = "/get-employees", method = RequestMethod.GET)
    public result getEmployees(int currentPage,int pageSize,String name){
        log.info("{},{},{}",currentPage,pageSize,name);
        PageResult pageResult =employeeService.getEmployeesList(currentPage,pageSize,name);

        return result.success(pageResult);
    }
    @RequestMapping(value = "/get-employee/{id}", method = RequestMethod.GET)
    public result getEmployeesById(@PathVariable long id){
        Employee employee=new Employee();
        employee.setId(id);
        Employee employee1=employeeService.getEmployee(employee);
        log.info("{}",employee1);
        return result.success(employee1);
    }
    @RequestMapping(value = "/switch-status", method = RequestMethod.POST)
    public result changeUserStatus(HttpServletRequest request,@RequestBody Employee employee){
        try{
            log.info("{}",employee);
            Long id=Long.parseLong((String)request.getSession().getAttribute("id"));
            employee.setUpdateUser(id);
            employee.setUpdateTime(LocalDateTime.now());
            employeeService.changeEmployee(employee);
            return result.success();
        }catch (Error e){
            return result.error();
        }
    }
    @RequestMapping(value = "/update-employee", method = RequestMethod.POST)
    public result updateEmployee(HttpServletRequest request,@RequestBody Employee employee){
        try{
            log.info("更新{}",employee);
            employeeService.updateEmployee(employee);
            return result.success();

        }catch (Error e){
            return result.error();
        }

    }


}
