package com.example.fontend.dto;

import com.example.fontend.entity.Dish;
import com.example.fontend.entity.Setmeal;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@NoArgsConstructor
public class ComboDto extends Setmeal {
    private List<DishDto>comboList;

}
