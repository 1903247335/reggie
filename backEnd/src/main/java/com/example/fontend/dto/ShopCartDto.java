package com.example.fontend.dto;

import com.example.fontend.entity.ShoppingCart;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShopCartDto extends ShoppingCart {
    private int type;
}
