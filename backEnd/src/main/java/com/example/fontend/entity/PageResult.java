package com.example.fontend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

public class PageResult<T>{
    private List<T> List;
    private int toTal;


    public PageResult() {
    }

    public PageResult(List<T> List, int toTal) {
        this.List = List;
        this.toTal = toTal;
    }

    /**
     * 获取
     * @return List
     */
    public List<T> getList() {
        return List;
    }

    /**
     * 设置
     * @param List
     */
    public void setList(List<T> List) {
        this.List = List;
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
        return "PageResult{List = " + List + ", toTal = " + toTal + "}";
    }
}
