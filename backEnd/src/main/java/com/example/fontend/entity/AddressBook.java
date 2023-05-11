package com.example.fontend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 地址管理(AddressBook)实体类
 *
 * @author makejava
 * @since 2023-05-09 21:47:41
 */
@Data
@NoArgsConstructor
public class AddressBook implements Serializable {
    private static final long serialVersionUID = -70940647027420381L;
    /**
     * 主键
     */
    private String id;
    /**
     * 用户id
     */
    private String userId;
    /**
     * 收货人
     */
    private String consignee;
    /**
     * 性别 0 女 1 男
     */
    private Integer sex;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 省级区划编号
     */
    private String provinceCode;
    /**
     * 省级名称
     */
    private String provinceName;
    /**
     * 市级区划编号
     */
    private String cityCode;
    /**
     * 市级名称
     */
    private String cityName;
    /**
     * 区级区划编号
     */
    private String districtCode;
    /**
     * 区级名称
     */
    private String districtName;
    /**
     * 详细地址
     */
    private String detail;
    /**
     * 标签
     */
    private String label;
    /**
     * 默认 0 否 1是
     */
    private Integer isDefault;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    /**
     * 更新时间
     */
    private LocalDateTime updateTime;
    /**
     * 创建人
     */
    private String createUser;
    /**
     * 修改人
     */
    private String updateUser;
    /**
     * 是否删除
     */
    private Integer isDeleted;



}

