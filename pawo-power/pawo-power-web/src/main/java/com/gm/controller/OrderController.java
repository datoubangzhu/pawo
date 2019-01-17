/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-power All rights reserved.
 */

package com.gm.controller;

import com.gm.dubbo.impl.OrderService;
import com.gm.user.ShoppingOrdersRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-16 22:24  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("pawo/order")
public class OrderController {


    private final OrderService orderService;


    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @RequestMapping("submit/fast")
    public void handleOrder(){
        ShoppingOrdersRequest ordersRequest = new ShoppingOrdersRequest();
        ordersRequest.setSn("201901160000000001");
        ordersRequest.setGoodsCode("Diro");
        orderService.fastSubmit(ordersRequest);
    }
}
