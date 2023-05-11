package com.example.fontend.service;

import com.example.fontend.common.PublicFill;
import com.example.fontend.dto.ComboDto;
import com.example.fontend.dto.DishDto;
import com.example.fontend.dto.ShopCartDto;
import com.example.fontend.entity.*;
import com.example.fontend.mail.Mail;
import com.example.fontend.mapper.UserMapper;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.qcloud.cos.utils.Jackson;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.example.fontend.untils.untils;

@Service
@Slf4j
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

    public User getUser(String email) {
        User user=userMapper.getUser(email);

        if(user!=null){

            return user;
        }else{
            return null;
        }
    }

    public void createUser(User user) {
        userMapper.createUser(user);
    }

    public List<Category> getCategories() {
        return userMapper.getCategories();
    }

    public List<?> getFoods(String id,int type) {
        if(type==1){
            List<DishDto>dtoList=userMapper.getDish(id);
            dtoList.stream().forEach((item)->{
                String detailFlavor= getDetailFlavors(String.valueOf(item.getId()));
                String mainFlavor=getMainFlavors(String.valueOf(item.getId()));
                item.setDetailFlavor(detailFlavor);
                item.setMainFlavor(mainFlavor);
            });

            return dtoList;
        }else{
            List<ComboDto>comboDtoList=userMapper.getCombo(id);

            comboDtoList.stream().forEach((item)->{
                List<SetmealDish> dishDtos=userMapper.getComboDish(String.valueOf(item.getId()));
                item.setSetmealDishes(dishDtos);
            });
            return comboDtoList;
        }

    }

    private String getMainFlavors(String id) {
        List<DishFlavor>dishFlavors=userMapper.getFlavors(id);
        List<String> strings=new ArrayList<String>();

        dishFlavors.stream().forEach((item)->{
            strings.add(item.getName());
        });


        return Jackson.toJsonString(strings);
    }

    public String getDetailFlavors(String id) {

        List<DishFlavor>dishFlavors=userMapper.getFlavors(id);
        List<List<String>> strings=new ArrayList<>();
        ObjectMapper objectMapper=new ObjectMapper();
        dishFlavors.stream().forEach((item)->{
            try {
                strings.add(objectMapper.readValue(item.getValue(),List.class));
            } catch (JsonProcessingException e) {
                throw new RuntimeException(e);
            }
        });

        return Jackson.toJsonString(strings);
    }

    public void addShopCart(ShopCartDto shoppingCart) {


            userMapper.addShopCart(shoppingCart);


    }

    public void updateShopCart(ShoppingCart shoppingCart) {
        log.info("{}",shoppingCart);

        userMapper.updateShopCart(shoppingCart);
    }

    public List<ShoppingCart> getShopCart(String id) {
        return userMapper.getShopCart(id);
    }

    public void deleteShopCarts(String id) {
        userMapper.deleteShopCarts(id);
    }

    public void addOrder(Orders orders) {
        orders.setId(String.valueOf(untils.createdUUID()));
        userMapper.addOrder(orders);

        String userId=orders.getUserId();
        List<ShoppingCart> shoppingCarts=userMapper.getShopCart(userId);
        shoppingCarts.stream().forEach((item)->{
            OrderDetail orderDetail=new OrderDetail();
            orderDetail.setId(String.valueOf(untils.createdUUID()));
            orderDetail.setName(item.getName());
            orderDetail.setOrderId(item.getId());
            if(item.getDishId()!=null){

                orderDetail.setDishId(String.valueOf(item.getDishId()));
            } else if (item.getSetmealId()!=null) {
                orderDetail.setSetmealId(String.valueOf(item.getSetmealId()));
            }

            orderDetail.setDishFlavor(item.getDishFlavor());
            orderDetail.setNumber(item.getNumber());
            orderDetail.setAmount(item.getAmount());
            orderDetail.setImage(item.getImage());
            log.info("{}",orderDetail);
            userMapper.addOrderDetail(orderDetail);
        });
        userMapper.deleteShopCarts(userId);
    }

    public void addAddressBook(HttpServletRequest httpServletRequest,AddressBook addressBook) {
        addressBook.setId(String.valueOf(untils.createdUUID()));
        String userId=addressBook.getUserId();
        addressBook.setCreateTime(LocalDateTime.now());
        addressBook.setUpdateTime(LocalDateTime.now());
        addressBook.setCreateUser(userId);
        addressBook.setUpdateUser(userId);

        userMapper.addAddressBook(addressBook);
    }

    public List<AddressBook> getAddress(String id) {
       return userMapper.getAddress(id);
    }

    public AddressBook getAddressByAddressBookId(String addressbookId) {
        return userMapper.getAddressByAddressBookId(addressbookId);
    }

    public void deleteShopCart(ShoppingCart shoppingCart) {
        userMapper.deleteShopCart(shoppingCart);
    }
}
