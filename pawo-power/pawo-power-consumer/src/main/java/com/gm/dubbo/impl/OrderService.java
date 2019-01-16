/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */

package com.gm.dubbo.impl;

import com.gm.user.ShoppingOrdersRequest;
import com.gm.user.service.IDubboOrderService;

import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;


/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-16 23:05  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class OrderService {

    @Reference
    private IDubboOrderService  dubboOrderService;

    /**
     *  抢单调用dubbo rpc订单处理服务
     *
     * @param ordersRequest 请求信息
     */
    public void fastSubmit(ShoppingOrdersRequest ordersRequest){
        dubboOrderService.fastSubmit(ordersRequest);
    }

}
