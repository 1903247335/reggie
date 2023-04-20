package com.example.fontend.mapper;

import com.example.fontend.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import com.example.fontend.entity.Employee;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface EmployeeMapper {
     Employee getEmployee(Employee employee);
     Employee updateEmployee(Employee employee);

     void addEmployee(Employee employee);
     List<Employee> getEmployeeList(int currentIndex,int pageSize,String name);
     @Select("select count(*) from employee")
     int getAllPageSize();
     @Update("update employee set status=#{status},update_time=#{updateTime},update_user=#{updateUser} where id=#{id}")
     void changeEmployee(Employee employee);
}
