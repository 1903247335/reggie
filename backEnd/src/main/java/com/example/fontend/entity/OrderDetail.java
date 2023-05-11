package com.example.fontend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 订单明细表(OrderDetail)实体类
 *
 * @author makejava
 * @since 2023-05-09 21:47:26
 */
@Data
@NoArgsConstructor
public class OrderDetail implements Serializable {
    private static final long serialVersionUID = -96342031331356952L;
    /**
     * 主键
     */
    private String id;
    /**
     * 名字
     */
    private String name;
    /**
     * 图片
     */
    private String image;
    /**
     * 订单id
     */
    private String orderId;
    /**
     * 菜品id
     */
    private String dishId;
    /**
     * 套餐id
     */
    private String setmealId;
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




}

