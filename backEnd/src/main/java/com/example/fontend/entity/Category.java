package com.example.fontend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 菜品及套餐分类(Category)实体类
 *
 * @author makejava
 * @since 2023-04-21 09:26:38
 */
@Data
@NoArgsConstructor
public class Category extends BasicEntity implements Serializable {

    /**
     * 主键
     */
    private Long id;
    /**
     * 类型   1 菜品分类 2 套餐分类
     */
    private Integer type;
    /**
     * 分类名称
     */
    private String name;
    /**
     * 顺序
     */
    private Integer sort;

    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;



}

