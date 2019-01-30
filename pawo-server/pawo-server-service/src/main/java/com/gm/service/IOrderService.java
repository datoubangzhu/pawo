/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-server All rights reserved.
 */

package com.gm.service;

import com.gm.order.ShoppingOrdersRequest;

/**
 * <p> 服务端订单service</p>
 *
 * <pre> Created: 2019-01-30 12:17  </pre>
 * <pre> Project: pawo-server  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public interface IOrderService {


    /**
     * mq处理下单接口
     * @param shoppingOrdersRequest 请求信息
     */
    void mqSubmit(ShoppingOrdersRequest shoppingOrdersRequest);
}
