package com.example.fontend.dto;

import com.example.fontend.entity.Dish;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class DishDto extends Dish {
    private String mainFlavor;
    private String detailFlavor;
    private Integer count;
}
