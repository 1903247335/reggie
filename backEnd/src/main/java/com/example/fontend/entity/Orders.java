package com.example.fontend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 订单表(Orders)实体类
 *
 * @author makejava
 * @since 2023-05-09 21:47:10
 */
@Data
@NoArgsConstructor
public class Orders implements Serializable {
    private static final long serialVersionUID = 417362516208753841L;
    /**
     * 主键
     */
    private String  id;
    /**
     * 订单号
     */
    private String number;
    /**
     * 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     */
    private Integer status;
    /**
     * 下单用户
     */
    private String userId;
    /**
     * 地址id
     */
    private Long addressBookId;
    /**
     * 下单时间
     */
    private Date orderTime;
    /**
     * 结账时间
     */
    private LocalDateTime checkoutTime;
    /**
     * 支付方式 1微信,2支付宝
     */
    private Integer payMethod;
    /**
     * 实收金额
     */
    private Double amount;
    /**
     * 备注
     */
    private String remark;

    private String phone;

    private String address;

    private String userName;

    private String consignee;



}

