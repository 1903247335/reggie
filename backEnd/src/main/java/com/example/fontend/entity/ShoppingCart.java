package com.example.fontend.entity;

import com.example.fontend.entity.BasicEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;
@Data
@NoArgsConstructor
/**
 * 购物车(ShoppingCart)实体类
 *
 * @author makejava
 * @since 2023-05-07 15:28:30
 */
public class ShoppingCart extends BasicEntity implements Serializable {
    private static final long serialVersionUID = -41585663387431781L;
    /**
     * 主键
     */
    private String id;
    /**
     * 名称
     */
    private String name;
    /**
     * 图片
     */
    private String image;
    /**
     * 主键
     */
    private String userId;
    /**
     * 菜品id
     */
    private Long dishId;
    /**
     * 套餐id
     */
    private Long setmealId;
    /**
     * 口味
     */
    private String dishFlavor;
    /**
     * 数量
     */
    private Integer number;
    /**
     * 金额
     */
    private Double amount;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;




}

