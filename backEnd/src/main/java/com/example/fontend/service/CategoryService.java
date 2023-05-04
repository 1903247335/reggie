package com.example.fontend.service;

import com.example.fontend.entity.Category;
import com.example.fontend.entity.Page;
import com.example.fontend.entity.PageResult;
import com.example.fontend.mapper.CategoryMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import com.example.fontend.untils.untils;

import java.util.List;


@Slf4j
@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    public void addCategory(Category category){
        Category tell=categoryMapper.getCategory(category);
        if (tell==null){
            category.setId(untils.createdUUID());
            log.info("{}",category);
            categoryMapper.addCategory(category);
        }else {
            throw new DuplicateKeyException("Duplicate category");
        }

    }

    public PageResult getCategories(Page page) {
        int currentIndex=(page.getCurrentPage()-1)* page.getPageSize();
        int toTal=categoryMapper.getAllPageSize();
        log.info("{},{}",currentIndex,toTal);
        List<Category>categoryList=categoryMapper.getCategories(currentIndex, page.getPageSize());
        return new PageResult(categoryList,toTal);
    }
    public List<Category> getCategoriesByType(int type) {

            return categoryMapper.getCategoriesByType(type);
    }

    public void deleteCategory(long id,int type) {
        if (type==1 ||type==2){
            log.info("{},{},{}",categoryMapper.getDish(id),categoryMapper.getSetmealDish(id),categoryMapper.getSetmeal(id));
            if(!categoryMapper.getDish(id).isEmpty()||
                    !categoryMapper.getSetmeal(id).isEmpty()||
            !categoryMapper.getSetmealDish(id).isEmpty()){
                throw new DataIntegrityViolationException("");
            }else{
                categoryMapper.deleteCategory(id);
            }
        }

    }

    public void updateCategory(Category category) {
        log.info("{}",category);
        categoryMapper.updateCategory(category);
    }
}
