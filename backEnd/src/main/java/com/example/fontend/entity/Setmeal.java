package com.example.fontend.entity;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 套餐(Setmeal)实体类
 *
 * @author makejava
 * @since 2023-04-22 00:05:54
 */
public class Setmeal extends BasicEntity implements Serializable {
    private static final long serialVersionUID = 679609874065107706L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 菜品分类id
     */
    private Long categoryId;
    /**
     * 套餐名称
     */
    private String name;
    /**
     * 套餐价格
     */
    private Double price;
    /**
     * 状态 0:停用 1:启用
     */
    private Integer status;
    /**
     * 编码
     */
    private String code;
    /**
     * 描述信息
     */
    private String description;
    /**
     * 图片
     */
    private String image;
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


    public Setmeal() {
    }

    public Setmeal(Long id, Long categoryId, String name, Double price, Integer status, String code, String description, String image, LocalDateTime createTime, LocalDateTime updateTime, Long createUser, Long updateUser, Integer isDeleted) {

        this.id = id;
        this.categoryId = categoryId;
        this.name = name;
        this.price = price;
        this.status = status;
        this.code = code;
        this.description = description;
        this.image = image;
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
     * @return categoryId
     */
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * 设置
     * @param categoryId
     */
    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
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
     * @return status
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取
     * @return code
     */
    public String getCode() {
        return code;
    }

    /**
     * 设置
     * @param code
     */
    public void setCode(String code) {
        this.code = code;
    }

    /**
     * 获取
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 获取
     * @return image
     */
    public String getImage() {
        return image;
    }

    /**
     * 设置
     * @param image
     */
    public void setImage(String image) {
        this.image = image;
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
        return "Setmeal{serialVersionUID = " + serialVersionUID + ", id = " + id + ", categoryId = " + categoryId + ", name = " + name + ", price = " + price + ", status = " + status + ", code = " + code + ", description = " + description + ", image = " + image + ", createTime = " + createTime + ", updateTime = " + updateTime + ", createUser = " + createUser + ", updateUser = " + updateUser + ", isDeleted = " + isDeleted + "}";
    }
}

