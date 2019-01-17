package com.gm.page.util;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. 赵贵明.
 * pawo-power All rights reserved.
 */


import java.io.Serializable;

import lombok.Data;

/**
 * <p> 分页请求参数 </p>
 *
 * <pre> Created: 2018-11-26 14:42  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class PageRequest implements Serializable {

    private static final long serialVersionUID = -7593422528654768240L;
    /**
     * 当前页码(默认从第 1 页开始)
     */
    private int page = 1;
    /**
     * 每页显示数量(默认每页显示10条)
     */
    private int size = 10;
}
