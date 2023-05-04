package com.example.fontend.mapper;

import com.example.fontend.entity.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper

public interface CategoryMapper {

    //添加
    void addCategory(Category category);
    @Select("select * from category where name=#{name}")
    Category getCategory(Category category);

    //查询
    List<Category> getCategories(int currentIndex,int pageSize);
    @Select("select count(*) from category")
    int getAllPageSize();

    //删除
    @Delete("delete from category where id=#{id}")
    void deleteCategory(long id);

    //删除之前查询联系

    //菜种类查询
    @Select("select * from dish where id=#{id}")
    List<Dish> getDish(long id);

    @Select("select * from setmeal where id=#{id}")
    List<Setmeal> getSetmeal(long id);
   //套餐种类查询
    @Select("select * from setmeal_dish where id=#{id}")
    List<SetmealDish> getSetmealDish(long id);
    @Update("update category set name=#{name},sort=#{sort} where id=#{id}")
    void updateCategory(Category category);
    @Select("select * from category where type=#{type}")
    List<Category> getCategoriesByType(int type);
}
