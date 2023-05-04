package com.example.fontend.controller;

import com.example.fontend.common.PublicFill;
import com.example.fontend.common.result;
import com.example.fontend.cos.Coslient;
import com.example.fontend.cos.PutObject;
import com.example.fontend.dto.DishDto;
import com.example.fontend.entity.Dish;
import com.example.fontend.entity.DishFlavor;
import com.example.fontend.entity.Page;
import com.example.fontend.entity.PageResult;
import com.example.fontend.service.FoodService;
import com.example.fontend.untils.untils;
import com.qcloud.cos.model.ObjectMetadata;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/font-end")
@Slf4j
public class FoodController {
    @Autowired
    private FoodService foodService;
    @RequestMapping(value = "/get-foods",method = RequestMethod.GET)
    public result getFoods(Page page){
        try {
            log.info("{}",page);
            PageResult pageResult = foodService.getFoods(page);
            return result.success(pageResult);
        }
        catch (Exception exception){
            return result.error();
        }
    }
    @RequestMapping(value = "/add-food",method = RequestMethod.POST)
    public result addFood(HttpServletRequest httpServletRequest,@RequestBody DishDto dish) throws IOException {
            DishFlavor dishFlavor=new DishFlavor();
            dishFlavor.setName(dish.getMainFlavor());
            dishFlavor.setValue(dish.getDetailFlavor());
            dish.setCode("");
            dish.setId(untils.createdUUID());
            dishFlavor.setId(untils.createdUUID());
            dishFlavor.setDishId(dish.getId());
            PublicFill.fill(httpServletRequest,dishFlavor);
            PublicFill.fill(httpServletRequest,dish);
            foodService.addFood(dish,dishFlavor);
            return result.success();

    }

    @RequestMapping(value = "/upload-template",method = RequestMethod.POST)
    public result uploadTemplate(MultipartFile file) throws IOException {
        //图片上传后会临时存储在
        String name=untils.combinationName(file);
        file.transferTo(new File("E:\\python\\save\\作品\\web\\project\\reggie\\fontEnd\\src\\main\\resources\\static\\image\\"+name));
        return result.success(name);
        //file.transferTo(new File("E:\\python\\save\\作品\\web\\project\\reggie\\fontEnd\\src\\main\\resources\\static\\image\\"+ UUID.randomUUID()+suffix));
        //String name=foodService.uploadFoodPng(file);
        //return result.success(name);
    }
    @RequestMapping(value = "/get-food/{id}",method = RequestMethod.GET)
    public result getFood(@PathVariable long id){
        DishDto dishDto=foodService.getFood(id);
        return result.success(dishDto);
    }
    @RequestMapping(value = "/update-food",method =RequestMethod.POST)
    public result updateFood(@RequestBody DishDto dishDto,HttpServletRequest httpServletRequest) throws IOException {
            foodService.updateFood(dishDto,httpServletRequest);
            return result.success();
    }
    @RequestMapping(value = "/get-food-by-category/{id}",method =RequestMethod.GET)
    public result getFoodByCategory(@PathVariable String id){
        List<Dish>dishList=foodService.getFoodByCategory(id);
        return result.success(dishList);
    }
}
