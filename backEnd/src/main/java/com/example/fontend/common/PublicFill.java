package com.example.fontend.common;

import com.example.fontend.entity.BasicEntity;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
@Slf4j
public  class PublicFill {
    public static  <T extends BasicEntity> void fill(HttpServletRequest request, T entity){
        entity.setCreateTime(LocalDateTime.now());
        entity.setUpdateTime(LocalDateTime.now());
        long id=Long.parseLong((String)request.getSession().getAttribute("id"));
        entity.setCreateUser(id);
        entity.setUpdateUser(id);


    }
}
