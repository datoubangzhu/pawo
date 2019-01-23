/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. gmzhao Inc.
 * pawo-common All rights reserved.
 */

package com.gm.order;

import com.gm.domain.TransactionTypeEnum;

import org.springframework.jmx.export.annotation.ManagedNotification;

import java.io.Serializable;

import lombok.Data;
import lombok.NonNull;

/**
 * <p> 用户下单订单表Request </p>
 *
 * <pre> Created: 2019-01-16 22:05  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class ShoppingOrdersRequest implements Serializable {


    private static final long serialVersionUID = 7259597428549422928L;

    /**
     * 订单编号
     */
    private String              sn;
    /**
     * 下单类型（秒杀/下单）
     */
    @NonNull
    private TransactionTypeEnum transactionType;
    /**
     * 卖家编号
     */
    @NonNull
    private String              sellerSn;
    /**
     * 商品编号
     */
    @NonNull
    private String              goodsCode;
    /**
     * 价格
     */
    @NonNull
    private Double              price;
    /**
     * 下单数量
     */
    @NonNull
    private Integer             volume;
    /**
     * 订单状态信息
     */
    @NonNull
    private String              remarkMsg;
    /**
     * 下单时间
     */
    private String              insertTime;
    /**
     * 用户编号
     */
    private Integer             userId;
    /**
     * 用户姓名
     */
    @NonNull
    private String              userName;

}
