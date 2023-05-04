package com.example.fontend.service;

import com.example.fontend.entity.User;
import com.example.fontend.mail.Mail;
import com.example.fontend.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserMapper userMapper;
    private static String createCode;

    public static void setCreateCode(String code){
        UserService.createCode=code;
    }

    public void sendMailCode(String email) {
        String code= Mail.sendMail(email);
        setCreateCode(code);
    }
    public Boolean verifyCode(String code){
        if(code.equals(createCode)){
            return true;
        }else{
            return false;
        }
    }

    public Object getUser(String email) {
        User user=userMapper.getUser(email);
        if(user!=null){
            return user;
        }else{
            return false;
        }
    }

    public void createUser(User user) {
        userMapper.createUser(user);
    }
}
