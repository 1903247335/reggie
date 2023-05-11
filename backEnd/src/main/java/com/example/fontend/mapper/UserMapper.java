package com.example.fontend.mapper;

import com.example.fontend.common.result;
import com.example.fontend.dto.ComboDto;
import com.example.fontend.dto.DishDto;
import com.example.fontend.dto.ShopCartDto;
import com.example.fontend.entity.*;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {
    @Select("select * from user where phone=#{email}")
    User getUser(String email);


    void createUser(User user);
    @Select("select * from category")
    List<Category> getCategories();
    @Select("select * from dish_flavor where dish_id=#{id}")
    List<DishFlavor> getFlavors(String id);

    @Select("select * from dish where category_id=#{id}")
    List<DishDto> getDish(String id);
    @Select("select * from setmeal where category_id=#{id}")
    List<ComboDto> getCombo(String id);
    @Select("select * from setmeal_dish where setmeal_id=#{id}")
    List<SetmealDish> getComboDish(String id);



    void addShopCart(ShopCartDto shoppingCart);


    void updateShopCart(ShoppingCart shoppingCart);
    @Select("select * from shopping_cart where user_id=#{id}")
    List<ShoppingCart> getShopCart(String id);
    @Delete("delete from shopping_cart where user_id=#{id}")
    void deleteShopCarts(String id);

    void addOrder(Orders orders);

    void addOrderDetail(OrderDetail orderDetail);

    void addAddressBook(AddressBook addressBook);
    @Select("select * from address_book where user_id=#{id}")
    List<AddressBook> getAddress(String id);
    @Select("select * from address_book where id=#{addressbookId}")
    AddressBook getAddressByAddressBookId(String addressbookId);

    void deleteShopCart(ShoppingCart shoppingCart);
}
