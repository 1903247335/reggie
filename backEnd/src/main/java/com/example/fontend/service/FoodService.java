package com.example.fontend.service;

import com.example.fontend.cos.PutObject;
import com.example.fontend.dto.DishDto;
import com.example.fontend.entity.Dish;
import com.example.fontend.entity.DishFlavor;
import com.example.fontend.entity.Page;
import com.example.fontend.entity.PageResult;
import com.example.fontend.mapper.FoodMapper;
import com.qcloud.cos.model.ObjectMetadata;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import com.example.fontend.untils.untils;

@Service
@Slf4j
public class FoodService {
    @Autowired
    private FoodMapper foodMapper;
    public PageResult getFoods(Page page){
        int currentIndex =(page.getCurrentPage()-1)*page.getPageSize();

        List<Dish>dishList=foodMapper.getFoods(currentIndex,page.getPageSize(),page.getName());

        int pageCount=foodMapper.getPageCount();

        return new PageResult(dishList,pageCount);
    }

    public void addFood(Dish dish, DishFlavor dishFlavor) throws IOException {

        foodMapper.addFood(dish);
        log.info("{}",dish);
        untils.uploadFoodPng(dish.getImage());
        foodMapper.addFoodFlavor(dishFlavor);
    }



    public DishDto getFood(long id) {
        DishDto dish=foodMapper.getDish(id);
        DishFlavor flavor = foodMapper.getFlavor(id);
        dish.setDetailFlavor(flavor.getValue());
        dish.setMainFlavor(flavor.getName());
        return dish;
    }

    public void updateFood(DishDto dishDto, HttpServletRequest httpServletRequest) throws IOException {
        dishDto.setUpdateTime(LocalDateTime.now());
        Long id=Long.parseLong((String)httpServletRequest.getSession().getAttribute("id"));
        dishDto.setUpdateUser(id);

        foodMapper.updateFood(dishDto);
        log.info("{}",dishDto);
        if(dishDto.getImage()!=null) {
            untils.uploadFoodPng(dishDto.getImage());
        }
        if(dishDto.getDetailFlavor()!=null){
            foodMapper.updateFoodFlavor(dishDto);
        }

    }

    public List<Dish> getFoodByCategory(String id) {
        return foodMapper.getFoodByCategory(id);
    }

    public void deleteFood(String id) {
        foodMapper.deleteFood(id);
        foodMapper.deleteFoodFlavor(id);
    }
}
