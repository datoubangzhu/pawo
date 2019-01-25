/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. gmzhao Inc.
 * pawo-power All rights reserved.
 */

package com.gm.dubbo.service.impl;

import org.springframework.stereotype.Component;
import com.alibaba.dubbo.config.annotation.Reference;
import com.gm.dubbo.service.IRpcOrderService;
import com.gm.order.ShoppingOrderVo;
import com.gm.order.ShoppingOrdersRequest;
import com.gm.service.IDubboOrderService;


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
public class RpcOrderServiceImpl implements IRpcOrderService {

    @Reference
    private IDubboOrderService dubboOrderService;

    /**
     *  抢单调用dubbo rpc订单处理服务
     *
     * @param ordersRequest 请求信息
     */
    public ShoppingOrderVo fastSubmit(ShoppingOrdersRequest ordersRequest){
       return dubboOrderService.fastSubmit(ordersRequest);
    }

}
