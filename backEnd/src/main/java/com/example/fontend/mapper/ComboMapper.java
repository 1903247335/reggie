package com.example.fontend.mapper;

import com.example.fontend.entity.Page;
import com.example.fontend.entity.Setmeal;
import com.example.fontend.entity.SetmealDish;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface ComboMapper {
    @Select("select count(*) from setmeal")
    int getAllPageSize();

    List<Setmeal> getCombList(int currentIndex,int pageSize);
    void editCombo(Setmeal setmeal);

    void addCombo(Setmeal setmeal);
    void addComboDish(SetmealDish setmealDish);
    @Delete("delete from setmeal where id=#{id}")
    void removeCombo(long id);
    @Delete("delete from setmeal_dish where setmeal_id=#{id}")
    void removeDish(long id);
}
