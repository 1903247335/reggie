package com.example.fontend.mapper;

import com.example.fontend.common.result;
import com.example.fontend.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    @Select("select * from user where phone=#{email}")
    User getUser(String email);


    void createUser(User user);
}
