/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明.
 * pawo-power All rights reserved.
 */

package com.gm.request;


import cn.hutool.core.date.DatePattern;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * <p> 用户信息类 </p>
 *
 * <pre> Created: 2019-01-02 17:36  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class UserRequest implements Serializable {

    private static final long serialVersionUID = -6320710789035784539L;
    /**
     * 用户名
     */
    @NotBlank(message =  "姓名不得为空")
    private String  username;
    /**
     * 密码
     */
    @NotBlank
    private String  password;
    /**
     * 是否为超级管理员
     */
    @NotNull
    private Boolean type;
    /**
     * 创建时间————————————————————//为何不生效
     */
    @DateTimeFormat(pattern = DatePattern.NORM_DATETIME_PATTERN)
//    @DateTimeFormat(pattern = DatePattern.NORM_DATE_PATTERN)
//    @JsonFormat(pattern = DatePattern.NORM_DATETIME_PATTERN,timezone = "GMT+8")
    private Date date;
}
