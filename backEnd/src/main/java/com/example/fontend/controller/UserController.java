package com.example.fontend.controller;

import com.example.fontend.common.result;
import com.example.fontend.entity.User;
import com.example.fontend.mail.Mail;
import com.example.fontend.mapper.UserMapper;
import com.example.fontend.service.UserService;
import com.example.fontend.untils.untils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/front-end")
public class UserController {
    @Autowired
    UserService userService;
    @RequestMapping(value = "/send-mail",method = RequestMethod.POST)
    public result SendMailCode(HttpServletRequest httpServletRequest, @RequestBody Map<String, String> email){

        try{

            userService.sendMailCode(email.get("email"));
            httpServletRequest.getSession().setAttribute("email", email.get("email"));
            return result.success();
        }catch (Error e){
            return result.error();
        }

    }
    @RequestMapping(value = "/verify-code",method = RequestMethod.POST)
    public result verifyCode(HttpServletRequest httpServletRequest,@RequestBody Map<String,String>code){
           log.info("{}",code);
           String email= (String) httpServletRequest.getSession().getAttribute("email");
           Boolean tell= userService.verifyCode(code.get("code"));
           User user=new User();
           Object res=userService.getUser(email);
           if(res!=null){
               user= (User) res;
               log.info("不为空");
           }else{
               log.info("为空{}",email);

               user.setId(untils.createdUUID());
               user.setName(email);
               user.setSex("0");
               user.setPhone(email);
               user.setIdNumber("");
               user.setStatus(1);
               userService.createUser(user);
           }
           if(tell){
                httpServletRequest.getSession().setAttribute("user",user.getId());
               return result.success();
           }
           else {
               return result.error();
           }
    }
}
