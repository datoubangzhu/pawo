/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-power All rights reserved.
 */

package com.gm.controller;

import com.gm.dubbo.service.RpcOrderService;
import com.gm.request.OrderRequest;
import com.gm.service.IOrderService;
import com.gm.user.ShoppingOrdersRequest;

import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> 下单控制层 </p>
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


    private final RpcOrderService rpcOrderService;
    private final IOrderService orderService;
    private final MapperFacade mapperFacade;


    @Autowired
    public OrderController(RpcOrderService rpcOrderService,
                           IOrderService orderService,
                           MapperFacade mapperFacade) {
        this.orderService = orderService;
        this.mapperFacade = mapperFacade;
        this.rpcOrderService = rpcOrderService;
    }


    /**
     * 下单
     *
     * @param shoppingOrder 订单信息
     */
    @RequestMapping("submit")
    public void handleOrder(ShoppingOrdersRequest shoppingOrder){
        orderService.submit(shoppingOrder);
    }



    /**
     * 抢单下单
     *
     * @param shoppingOrder 订单信息
     */
    @RequestMapping("submit/fast")
    public void handleOrderFast(ShoppingOrdersRequest orderRequest){
        rpcOrderService.fastSubmit(orderRequest);
    }
}
