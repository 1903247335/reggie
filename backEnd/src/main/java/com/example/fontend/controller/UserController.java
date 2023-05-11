package com.example.fontend.controller;

import com.example.fontend.common.result;
import com.example.fontend.dto.ComboDto;
import com.example.fontend.dto.DishDto;
import com.example.fontend.dto.ShopCartDto;
import com.example.fontend.entity.*;
import com.example.fontend.mail.Mail;
import com.example.fontend.mapper.UserMapper;
import com.example.fontend.service.UserService;
import com.example.fontend.untils.untils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
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

            return result.success();
        }catch (Error e){
            return result.error();
        }

    }
    @RequestMapping(value = "/verify-code",method = RequestMethod.POST)
    public result verifyCode(HttpServletRequest httpServletRequest,@RequestBody Map<String,String>data){
            String code=data.get("code");
            String email=data.get("email");
           Boolean tell= userService.verifyCode(code);//验证验证码是否相同
           User user=new User();
           User res=userService.getUser(email);
           if(res!=null){
               user= res;//里面查找到数据不是注册用户

           }else{
                //查找不到证明是新注册
               user.setId(untils.createdUUID());
               user.setName(email);
               user.setSex("0");
               user.setPhone(email);
               user.setIdNumber("");
               user.setStatus(1);

           }
           if(tell){
                if(res==null){

                    userService.createUser(user);
                }
                return result.success(user);
           }
           else {
               return result.error();
           }
    }
    @RequestMapping(value = "/get-categories", method = RequestMethod.GET)
    public result getCategories(){
        try {
            List<Category> categories = userService.getCategories();
            return result.success(categories);
        }
        catch (Error e){
            return result.error();
        }
    }
    @RequestMapping(value = "/get-foods",method = RequestMethod.GET)
    public result getFoods(String id,int type){
        try{
            if(type==1) {
                List<?> dishes = userService.getFoods(id, type);
                return result.success(dishes);
            }
            else{
                List<?>comboDtos=userService.getFoods(id,type);
                return result.success(comboDtos);
            }
        }catch (Error e){
            return result.error();
        }
    }
    @RequestMapping(value = "/add-shop-cart",method = RequestMethod.POST)
    public result addShopCart(@RequestBody ShopCartDto shoppingCart){
        log.info("{}",shoppingCart);
        try {
            shoppingCart.setId(String.valueOf(untils.createdUUID()));
            shoppingCart.setCreateTime(LocalDateTime.now());
            userService.addShopCart(shoppingCart);
            return result.success();
        }
        catch (Error e){
            return result.error();
        }
    }
    @RequestMapping(value = "/update-shop-cart",method = RequestMethod.POST)
    public result updateShopCart(@RequestBody ShoppingCart shoppingCart){
        log.info("更新{}",shoppingCart);
        shoppingCart.setCreateTime(LocalDateTime.now());
        userService.updateShopCart(shoppingCart);
        return result.success();
    }
    @RequestMapping(value = "/get-shop-cart/{id}",method = RequestMethod.GET)
    public result getShopCart(@PathVariable String id){

        List<ShoppingCart>shoppingCarts=userService.getShopCart(id);
        return result.success(shoppingCarts);
    }
    @RequestMapping(value = "/delete-shop-carts/{id}",method = RequestMethod.POST)
    public result deleteShopCarts(@PathVariable String id){
        userService.deleteShopCarts(id);
        return result.success();
    }

    @RequestMapping(value = "/delete-shop-cart",method = RequestMethod.POST)
    public result deleteShopCart(@RequestBody ShoppingCart shoppingCart){
        log.info("{}",shoppingCart);
        userService.deleteShopCart(shoppingCart);
        return result.success();
    }

    @RequestMapping(value = "/add-order",method = RequestMethod.POST)
    public result addOrder(@RequestBody Orders orders){
        AddressBook addressBook=userService.getAddressByAddressBookId(String.valueOf(orders.getAddressBookId()));
        orders.setConsignee(addressBook.getConsignee());
        orders.setAddress(addressBook.getProvinceName()+addressBook.getCityName()+addressBook.getDistrictName()+addressBook.getDetail());
        orders.setPhone(addressBook.getPhone());
        orders.setUserId(addressBook.getUserId());
        orders.setUserName(addressBook.getPhone());
        orders.setCheckoutTime(LocalDateTime.now());
        log.info("{}",orders);
        userService.addOrder(orders);
        return result.success();
    }
    @RequestMapping(value = "/add-address",method = RequestMethod.POST)
    public result addAddressBook(HttpServletRequest httpServletRequest,@RequestBody AddressBook addressBook){
        userService.addAddressBook(httpServletRequest,addressBook);
        return result.success();
    }
    @RequestMapping(value = "/get-address/{id}",method = RequestMethod.GET)
    public result getAddress(@PathVariable String id){
        List<AddressBook> addressBooks=userService.getAddress(id);
        return result.success(addressBooks);
    }
    @RequestMapping(value = "/get-address-by-addressbookId/{addressbookId}",method =RequestMethod.GET)
    public result getAddressByAddressBookId(@PathVariable String addressbookId){
        AddressBook addressBook=userService.getAddressByAddressBookId(addressbookId);
        return result.success(addressBook);
    }


}
