/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明.
 * pawo-common All rights reserved.
 */

package com.gm.user;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明.
 * pawo-common All rights reserved.
 */


import java.io.Serializable;

import lombok.Data;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-15 23:31  </pre>
 * <pre> Project: pawo-common  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class DubboUserPo implements Serializable{

    private static final long serialVersionUID = 7933752753116095784L;
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
