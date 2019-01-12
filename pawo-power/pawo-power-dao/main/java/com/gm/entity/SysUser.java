/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */

package com.gm.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.google.common.base.MoreObjects;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

/**
 * <p> 系统用户entity </p>
 *
 * <pre> Created: 2019-01-12 17:22  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@TableName("SYS_USER")
public class SysUser extends Model<SysUser>{

    private static final long serialVersionUID = 7579574407234534547L;

    @TableId
    private Long iid;
    /**
     * 用户名
     */
    @TableField("name")
    private String username;
    /**
     * 密码
     */
    private String  password;
    /**
     * 是否为超级管理员
     */
    private boolean type;
    /**
     * 创建时间
     */
    private String    createTime;
    /**
     * 删除标志
     */
    @TableLogic
    private Integer deleteFlag;


    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isType() {
        return type;
    }

    public void setType(boolean type) {
        this.type = type;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    protected Serializable pkVal() {
        return this.iid;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("username", username)
                .add("password", password)
                .add("type", type)
                .add("createTime", createTime)
                .add("deleteFlag", deleteFlag)
                .toString();
    }
}
