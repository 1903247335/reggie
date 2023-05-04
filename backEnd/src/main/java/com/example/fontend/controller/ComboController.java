package com.example.fontend.controller;

import com.example.fontend.common.PublicFill;
import com.example.fontend.common.result;
import com.example.fontend.dto.ComboDto;
import com.example.fontend.entity.Page;
import com.example.fontend.entity.PageResult;
import com.example.fontend.entity.Setmeal;
import com.example.fontend.service.ComboService;
import com.example.fontend.untils.untils;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/font-end")
public class ComboController {
    @Autowired
    private ComboService comboService;
    @RequestMapping("/get-combos")
    public result getCombos(Page page){
        try{

            PageResult combos=comboService.getCombos(page);
            return result.success(combos);
        }
        catch (Exception e){
            return result.error();
        }


    }
    @RequestMapping(value = "/edit-combo",method = RequestMethod.POST)
    public result editCombo(Setmeal setmeal){
        comboService.editCombo(setmeal);
        return result.success();
    }
    @RequestMapping(value = "/add-combo",method = RequestMethod.POST)
    public result addCombo(HttpServletRequest httpServletRequest, @RequestBody ComboDto comboDto) throws IOException {
        comboDto.setId(untils.createdUUID());
        PublicFill.fill(httpServletRequest,comboDto);
        log.info("{}",comboDto.getImage());
        untils.uploadFoodPng(comboDto.getImage());
        comboService.addCombo(httpServletRequest,comboDto);
        return result.success();
    }
    @RequestMapping(value = "/delete-combo/{id}",method = RequestMethod.POST)
    public result removeCombo(@PathVariable long id){
        comboService.removeCombo(id);
        return result.success();
    }
}
