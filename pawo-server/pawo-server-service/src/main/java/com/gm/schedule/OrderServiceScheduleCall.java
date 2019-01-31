/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-server All rights reserved.
 */

package com.gm.schedule;

import com.gm.order.ShoppingOrdersRequest;
import com.gm.service.impl.OrderServiceImpl;

import java.util.concurrent.Callable;

/**
 * <p> 下单接口多线程处理 </p>
 *
 * <pre> Created: 2019-01-31 14:50  </pre>
 * <pre> Project: pawo-server  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public class OrderServiceScheduleCall implements Callable {

    private final OrderServiceImpl orderService;
    private final ShoppingOrdersRequest request;

    public OrderServiceScheduleCall(OrderServiceImpl orderService, ShoppingOrdersRequest request) {
        this.orderService = orderService;
        this.request = request;
    }

    @Override
    public Object call() {
        return orderService.mqSubmit(request);
    }
}
