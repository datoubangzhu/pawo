/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. gmzhao Inc.
 * pawo-common All rights reserved.
 */

package com.gm.config.exception;

/**
 * <p> 错误码信息 </p>
 *
 * <pre> Created: 2019-01-17 16:58  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public enum PawoError {

    /**
     * 用户有关错误 10001~19999
     */
    AUTH_FAILURE(10001),

    /**
     * 用户创建失败
     */
    USER_CREATE_FAILURE(10002),


    /**
     * 订单有关错误 20001~29999
     */
    SUBMIT_FAILURE(20001),


    /**
     * 初始化数据有关错误 30001~39999
     */
    INIT_FAILURE(30001),
    /**
     * 初始化商品数据失败
     */
    INIT_GOODS_FAILURE(30002)
    ;


    private final int code;

    PawoError(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }
}
