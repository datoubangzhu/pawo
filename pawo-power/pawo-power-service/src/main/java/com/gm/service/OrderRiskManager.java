/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-power All rights reserved.
 */

package com.gm.service;

import com.gm.config.exception.PawoError;
import com.gm.config.exception.PawoException;
import com.gm.dubbo.service.IRpcOrderService;
import com.gm.dubbo.service.impl.RpcOrderServiceImpl;
import com.gm.order.ShoppingOrdersRequest;
import com.gm.service.impl.OrderServiceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import lombok.extern.slf4j.Slf4j;

/**
 * <p>
 *     反向代理动态实现风险控制。
 *     springaop 只能对具体一个实现类或一个包下的service实现切面。不能灵活的对不同接口及不同接口方法（参数）进行控制
 *     例如对多个方法的参数进行控制，那么方法参数位置都要一致，等等。
 * </p>
 *
 * <pre> Created: 2019-01-25 10:08  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Component
@Slf4j
public class OrderRiskManager {


    private final IOrderService    orderService;
    private final IRpcOrderService rpcOrderService;

    /**
     * 要进行下单检查的方法 submit
     */
    private final String SUBMIT_METHOD = "submit";
    /**
     * 要进行下单检查的方法 submit
     */
    private final String SUBMIT_CACHE_METHOD = "submitCache";
    /**
     * 要进行秒杀下单检查的方法 fastSubmit
     */
    private final String FAST_SUBMIT_METHOD = "fastSubmit";

    /**
     * 单次下单最高手数
     */
    private final int SINGLE_MAX_VOLUME = 10;

    @Autowired
    public OrderRiskManager(IOrderService orderService,
                            IRpcOrderService rpcOrderService) {
        this.orderService = orderService;
        this.rpcOrderService = rpcOrderService;
    }


    public IOrderService getOrderService() {
        return (IOrderService) Proxy.newProxyInstance(IOrderService.class.getClassLoader(),
                OrderServiceImpl.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        checkOrdinaryOrder(method,args);
                        return method.invoke(orderService, args);
                    }
                });
    }



    public IRpcOrderService getRpcOrderService() {
        return (IRpcOrderService) Proxy.newProxyInstance(IRpcOrderService.class.getClassLoader(),
                RpcOrderServiceImpl.class.getInterfaces(), new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                        checkFastOrder(method,args);
                        return method.invoke(orderService, args);
                    }
                });
    }




    private void checkOrdinaryOrder(Method method,Object[] args){
        String name = method.getName();
        if(!SUBMIT_METHOD.equals(name) && !SUBMIT_CACHE_METHOD.equals(name)){
            return;
        }
        log.info("普通下单前检查>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> \n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ShoppingOrdersRequest shoppingOrder = (ShoppingOrdersRequest)args[0];
        final int volume = shoppingOrder.getVolume();
        if(volume>SINGLE_MAX_VOLUME){
            log.error("下单检查失败，手数超过最大允许限值 {} ", volume);
            throw new PawoException("下单检查失败，手数超过最大允许限值",PawoError.SUBMIT_CHECK_FAILURE.getCode());
        }

    }



    private void checkFastOrder(Method method,Object[] args){
        String name = method.getName();
        if(!FAST_SUBMIT_METHOD.equals(name)){
            return;
        }
        log.info("快速下单前检查>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> \n >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");
        ShoppingOrdersRequest shoppingOrder = (ShoppingOrdersRequest)args[0];
        final int volume = shoppingOrder.getVolume();
        if(volume>SINGLE_MAX_VOLUME){
            log.error("下单检查失败，手数超过最大允许限值 {} ", volume);
            throw new PawoException("下单检查失败，手数超过最大允许限值>>>>>>>>>>>>>>",PawoError.SUBMIT_CHECK_FAILURE.getCode());
        }

    }


}