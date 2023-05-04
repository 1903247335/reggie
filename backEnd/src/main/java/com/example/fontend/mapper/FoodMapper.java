package com.example.fontend.mapper;

import com.example.fontend.dto.DishDto;
import com.example.fontend.entity.Dish;
import com.example.fontend.entity.DishFlavor;
import com.example.fontend.entity.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface FoodMapper {

    List<Dish> getFoods(int currentIndex,int pageSize,String name);
    @Select("select count(*) from dish")
    int getPageCount();

    void addFood(Dish dish);

    void addFoodFlavor(DishFlavor dishFlavor);


    DishDto getDish(long id);

    DishFlavor getFlavor(long id);

    void updateFood(DishDto dishDto);

    void updateFoodFlavor(DishDto dishDto);

    List<Dish> getFoodByCategory(String id);
}
