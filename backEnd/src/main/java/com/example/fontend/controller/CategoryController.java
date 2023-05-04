package com.example.fontend.controller;

import com.example.fontend.common.PublicFill;
import com.example.fontend.common.result;
import com.example.fontend.entity.Page;
import com.example.fontend.entity.PageResult;
import com.example.fontend.service.CategoryService;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.*;
import com.example.fontend.entity.Category;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/font-end")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
    @RequestMapping(value = "/add-category",method = RequestMethod.POST)
    public result addCategory(HttpServletRequest request, @RequestBody Category category){
        try{
            PublicFill.fill(request,category);

            categoryService.addCategory(category);

            return result.success();
        }
        catch (DuplicateKeyException e){
            return result.error();
        }

    }
    @RequestMapping(value = "/get-categories",method = RequestMethod.GET)
    public result getCategories(HttpServletRequest request, Page page){
        PageResult pageResult=categoryService.getCategories(page);
        return result.success(pageResult);
    }
    @RequestMapping(value = "/get-categories-by-type/{type}",method = RequestMethod.GET)
    public result getCategoriesByType(HttpServletRequest request, @PathVariable("type")int type){
        List<Category>categoryList=categoryService.getCategoriesByType(type);
        return result.success(categoryList);
    }

    @RequestMapping(value = "/delete-category/{id}/{type}",method = RequestMethod.DELETE)
    public result deleteCategory(HttpServletRequest request, @PathVariable long id,@PathVariable int type){
        try{
            categoryService.deleteCategory(id,type);
            log.info("删除{}",id);
            return result.success();
        }
        catch (Exception e){
            return result.error();
        }

    }
    @RequestMapping(value = "/update-category",method = RequestMethod.PUT)
    public result updateCategory(HttpServletRequest request,@RequestBody Category category){
        try {
            PublicFill.fill(request,category);
            categoryService.updateCategory(category);
            return result.success();
        }
        catch (Exception e){
            return result.error();
        }
    }


}
