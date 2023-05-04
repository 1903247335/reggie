package com.example.fontend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 菜品管理(Dish)实体类
 *
 * @author makejava
 * @since 2023-04-24 10:51:29
 */
@Data
@NoArgsConstructor
public class Dish extends BasicEntity implements Serializable {
    private static final long serialVersionUID = 127612034839224161L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 菜品名称
     */
    private String name;
    /**
     * 菜品分类id
     */
    private Long categoryId;
    /**
     * 菜品价格
     */
    private Double price;
    /**
     * 商品码
     */
    private String code;
    /**
     * 图片
     */
    private String image;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 0 停售 1 起售
     */
    private Integer status;
    /**
     * 顺序
     */
    private Integer sort;
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
    private Long createUser;
    /**
     * 修改人
     */
    private Long updateUser;
    /**
     * 是否删除
     */
    private Integer isDeleted;


}



