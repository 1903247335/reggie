package com.example.fontend.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 套餐菜品关系(SetmealDish)实体类
 *
 * @author makejava
 * @since 2023-04-22 00:06:19
 */
public class SetmealDish extends BasicEntity implements Serializable {
    private static final long serialVersionUID = -55648350779587506L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 套餐id
     */
    private String setmealId;
    /**
     * 菜品id
     */
    private String dishId;
    /**
     * 菜品名称 （冗余字段）
     */
    private String name;
    /**
     * 菜品原价（冗余字段）
     */
    private Double price;
    /**
     * 份数
     */
    private Integer copies;
    /**
     * 排序
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


    public SetmealDish() {
    }

    public SetmealDish(Long id, String setmealId, String dishId, String name, Double price, Integer copies, Integer sort, LocalDateTime createTime, LocalDateTime updateTime, Long createUser, Long updateUser, Integer isDeleted) {

        this.id = id;
        this.setmealId = setmealId;
        this.dishId = dishId;
        this.name = name;
        this.price = price;
        this.copies = copies;
        this.sort = sort;
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
     * @return setmealId
     */
    public String getSetmealId() {
        return setmealId;
    }

    /**
     * 设置
     * @param setmealId
     */
    public void setSetmealId(String setmealId) {
        this.setmealId = setmealId;
    }

    /**
     * 获取
     * @return dishId
     */
    public String getDishId() {
        return dishId;
    }

    /**
     * 设置
     * @param dishId
     */
    public void setDishId(String dishId) {
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
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
    }

    /**
     * 获取
     * @return copies
     */
    public Integer getCopies() {
        return copies;
    }

    /**
     * 设置
     * @param copies
     */
    public void setCopies(Integer copies) {
        this.copies = copies;
    }

    /**
     * 获取
     * @return sort
     */
    public Integer getSort() {
        return sort;
    }

    /**
     * 设置
     * @param sort
     */
    public void setSort(Integer sort) {
        this.sort = sort;
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
        return "SetmealDish{serialVersionUID = " + serialVersionUID + ", id = " + id + ", setmealId = " + setmealId + ", dishId = " + dishId + ", name = " + name + ", price = " + price + ", copies = " + copies + ", sort = " + sort + ", createTime = " + createTime + ", updateTime = " + updateTime + ", createUser = " + createUser + ", updateUser = " + updateUser + ", isDeleted = " + isDeleted + "}";
    }
}

