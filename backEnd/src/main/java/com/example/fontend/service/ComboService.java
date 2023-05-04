package com.example.fontend.service;

import com.example.fontend.common.PublicFill;
import com.example.fontend.dto.ComboDto;
import com.example.fontend.dto.DishDto;
import com.example.fontend.entity.*;
import com.example.fontend.mapper.ComboMapper;
import com.example.fontend.untils.untils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Slf4j
@Service
public class ComboService {
    @Autowired
    private ComboMapper comboMapper;
    public PageResult getCombos(Page page){
        int currentIndex=(page.getCurrentPage()-1)*page.getPageSize();
        int toTal=comboMapper.getAllPageSize();
        List<Setmeal> setmealList=comboMapper.getCombList(currentIndex,page.getPageSize());
        return new PageResult(setmealList,toTal);

    }

    public void editCombo(Setmeal setmeal) {
        comboMapper.editCombo(setmeal);
    }

    public void addCombo(HttpServletRequest request, ComboDto comboDto) {
        log.info("{}",comboDto);
        comboMapper.addCombo(comboDto);
        List<DishDto>comboList=comboDto.getComboList();
        comboList.stream().forEach((item)->{
            SetmealDish setmealDish=new SetmealDish();
            setmealDish.setId(untils.createdUUID());
            setmealDish.setSetmealId(String.valueOf(comboDto.getId()));
            setmealDish.setDishId(String.valueOf(item.getId()));
            setmealDish.setName(item.getName());
            setmealDish.setPrice(item.getPrice());
            setmealDish.setCopies(item.getCount());
            setmealDish.setSort(0);
            PublicFill.fill(request,setmealDish);
            comboMapper.addComboDish(setmealDish);
        });

    }

    public void removeCombo(long id) {
        comboMapper.removeCombo(id);
        comboMapper.removeDish(id);
    }
}
