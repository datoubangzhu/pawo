/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-power All rights reserved.
 */

package com.gm.controller;

import com.gm.config.exception.PawoError;
import com.gm.config.exception.PawoException;
import com.gm.dubbo.service.IRpcOrderService;
import com.gm.goods.GoodsOrders;
import com.gm.order.ShoppingOrderVo;
import com.gm.order.ShoppingOrdersRequest;
import com.gm.service.IOrderService;
import com.gm.risk.OrderRiskManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
@RequestMapping("pawo/order")
public class OrderController {

    private final OrderRiskManager    orderRiskManager;


    @Autowired
    public OrderController(OrderRiskManager orderRiskManager) {
        this.orderRiskManager = orderRiskManager;
    }


    /**
     * 下单
     *
     * @param shoppingOrder 订单信息
     */
    @RequestMapping(value = "submit", method = RequestMethod.POST)
    public ResponseEntity<ShoppingOrderVo> handleOrder(@RequestBody @Valid ShoppingOrdersRequest shoppingOrder,HttpServletResponse response,HttpServletRequest request){
        //数据库   ShoppingOrderVo shoppingOrderVo =  orderService.submit(shoppingOrder);
        IOrderService service =  orderRiskManager.getOrderService();
        ShoppingOrderVo shoppingOrderVo =  service.submitCache(shoppingOrder);
        return ResponseEntity.ok(shoppingOrderVo);
    }



    /**
     * 抢单下单
     *
     * @param shoppingOrder 订单信息
     */
    @RequestMapping("submit/fast")
    public ResponseEntity<ShoppingOrderVo> handleOrderFast(@RequestBody @Valid ShoppingOrdersRequest shoppingOrder,HttpServletResponse response){
        IRpcOrderService rpcOrderService = orderRiskManager.getRpcOrderService();
        ShoppingOrderVo shoppingOrderVo = rpcOrderService.fastSubmit(shoppingOrder);
        return  ResponseEntity.ok(shoppingOrderVo);
    }


    /**
     * 获取商品信息列表
     *
     * @return 商品信息
     */
    @GetMapping("goods/list")
    public ResponseEntity listGoods(){
        IOrderService service  = orderRiskManager.getOrderService();
        List<GoodsOrders> goodsOrders =  service.list();
        return ResponseEntity.ok(goodsOrders);
    }
}
