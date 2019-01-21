/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-power All rights reserved.
 */

package com.gm.controller;

import com.gm.dubbo.service.RpcOrderService;
import com.gm.order.ShoppingOrderVo;
import com.gm.order.ShoppingOrdersRequest;
import com.gm.service.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ma.glasnost.orika.MapperFacade;

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


    @Autowired
    public OrderController(RpcOrderService rpcOrderService,
                           IOrderService orderService) {
        this.orderService = orderService;
        this.rpcOrderService = rpcOrderService;
    }


    /**
     * 下单
     *
     * @param shoppingOrder 订单信息
     */
    @RequestMapping("submit")
    public ResponseEntity<ShoppingOrderVo> handleOrder(ShoppingOrdersRequest shoppingOrder){
        ShoppingOrderVo shoppingOrderVo =  orderService.submit(shoppingOrder);
        return ResponseEntity.ok(shoppingOrderVo);
    }



    /**
     * 抢单下单
     *
     * @param shoppingOrder 订单信息
     */
    @RequestMapping("submit/fast")
    public ResponseEntity<ShoppingOrderVo> handleOrderFast(ShoppingOrdersRequest shoppingOrder){
        rpcOrderService.fastSubmit(shoppingOrder);
        return (ResponseEntity<ShoppingOrderVo>) ResponseEntity.ok();

    }
}
