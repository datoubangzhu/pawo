package com.gm.page.util;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. 赵贵明.
 * pawo-power All rights reserved.
 */


import org.junit.Test;

import java.io.Serializable;
import java.util.List;

import lombok.Builder;
import lombok.Data;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-11-26 15:14  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Data
@Builder
public class PageResponse<Po extends Serializable> implements Serializable{

    private static final long serialVersionUID = 4111675380483230675L;
    /**
     * 当前页码
     */
    private int page;
    /**
     * 每页展示数量
     */
    private int size;
    /**
     * 总页数
     */
    private int pageTotal;
    /**
     * 总条数
     */
    private int total;
    /**
     * 结果信息
     */
    private List<Po> data;
    /**
     * 分页结果信息
     */
    private String message;
}
