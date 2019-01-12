/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */

package com.gm.po;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

import javax.validation.constraints.NotNull;

import cn.hutool.core.date.DatePattern;
import lombok.Builder;
import lombok.Data;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-02 20:45  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class UserPo implements Serializable{

    private static final long serialVersionUID = -2130727214286792267L;

    /**
     * 用户名
     */
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
}
