/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-server All rights reserved.
 */

package com.gm.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gm.mapper.OrderMapper;
import com.gm.user.ShoppingOrders;
import com.gm.user.ShoppingOrdersRequest;
import com.gm.user.service.IDubboOrderService;

import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> 订单处理rpc 实现类</p>
 *
 * <pre> Created: 2019-01-16 22:42  </pre>
 * <pre> Project: pawo-server  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Service
@Component
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper,ShoppingOrders> implements IDubboOrderService {


    @Override
    public boolean fastSubmit(ShoppingOrdersRequest ordersRequest) {
        final String sn = ordersRequest.getSn();
        log.info("收到订单信息，单号：{}",sn);
        return true;
    }
}
