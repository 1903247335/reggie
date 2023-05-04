package com.example.fontend.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;
import java.io.Serializable;

/**
 * 员工信息(Employee)实体类
 *
 * @author makejava
 * @since 2023-04-12 19:05:28
 */
@Data
@NoArgsConstructor
public class Employee extends BasicEntity implements Serializable {
    private static final long serialVersionUID = 548558735581028906L;
    /**
     * 主键
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 手机号
     */
    private String phone;
    /**
     * 性别
     */
    private String sex;
    /**
     * 身份证号
     */
    private String idNumber;
    /**
     * 状态 0:禁用，1:正常
     */
    private Integer status;
    /**
     * 创建时间
     */
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Long createUser;
    private Long updateUser;





}

