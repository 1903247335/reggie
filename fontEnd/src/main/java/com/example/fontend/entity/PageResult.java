package com.example.fontend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class PageResult {
    private List<Employee> employeeList;
    private int toTal;

    public PageResult() {
    }

    public PageResult(List<Employee> employeeList, int toTal) {
        this.employeeList = employeeList;
        this.toTal = toTal;
    }

    /**
     * 获取
     * @return employeeList
     */
    public List<Employee> getEmployeeList() {
        return employeeList;
    }

    /**
     * 设置
     * @param employeeList
     */
    public void setEmployeeList(List<Employee> employeeList) {
        this.employeeList = employeeList;
    }

    /**
     * 获取
     * @return toTal
     */
    public int getToTal() {
        return toTal;
    }

    /**
     * 设置
     * @param toTal
     */
    public void setToTal(int toTal) {
        this.toTal = toTal;
    }

    public String toString() {
        return "PageResult{employeeList = " + employeeList + ", toTal = " + toTal + "}";
    }
}
