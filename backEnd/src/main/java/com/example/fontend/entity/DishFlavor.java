package com.example.fontend.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 菜品口味关系表(DishFlavor)实体类
 *
 * @author makejava
 * @since 2023-04-26 19:48:57
 */
public class DishFlavor extends BasicEntity implements Serializable  {
    private static final long serialVersionUID = -62075382693081169L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 菜品
     */
    private Long dishId;
    /**
     * 口味名称
     */
    private String name;
    /**
     * 口味数据list
     */
    private String value;
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


    public DishFlavor() {
    }

    public DishFlavor( Long id, Long dishId, String name, String value, LocalDateTime createTime, LocalDateTime updateTime, Long createUser, Long updateUser, Integer isDeleted) {

        this.id = id;
        this.dishId = dishId;
        this.name = name;
        this.value = value;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.createUser = createUser;
        this.updateUser = updateUser;
        this.isDeleted = isDeleted;
    }

    /**
     * 获取
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return dishId
     */
    public Long getDishId() {
        return dishId;
    }

    /**
     * 设置
     * @param dishId
     */
    public void setDishId(Long dishId) {
        this.dishId = dishId;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return value
     */
    public String getValue() {
        return value;
    }

    /**
     * 设置
     * @param value
     */
    public void setValue(String value) {
        this.value = value;
    }

    /**
     * 获取
     * @return createTime
     */
    public LocalDateTime getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     * @param createTime
     */
    public void setCreateTime(LocalDateTime createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取
     * @return updateTime
     */
    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置
     * @param updateTime
     */
    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取
     * @return createUser
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * 设置
     * @param createUser
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取
     * @return updateUser
     */
    public Long getUpdateUser() {
        return updateUser;
    }

    /**
     * 设置
     * @param updateUser
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取
     * @return isDeleted
     */
    public Integer getIsDeleted() {
        return isDeleted;
    }

    /**
     * 设置
     * @param isDeleted
     */
    public void setIsDeleted(Integer isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String toString() {
        return "DishFlavor{id = " + id + ", dishId = " + dishId + ", name = " + name + ", value = " + value + ", createTime = " + createTime + ", updateTime = " + updateTime + ", createUser = " + createUser + ", updateUser = " + updateUser + ", isDeleted = " + isDeleted + "}";
    }
}

