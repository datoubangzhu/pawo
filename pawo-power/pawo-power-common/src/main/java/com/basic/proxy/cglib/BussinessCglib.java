/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-power All rights reserved.
 */

package com.basic.proxy.cglib;


import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> cglib 实现动态代理 </p>
 *
 * <pre> Created: 2019-02-13 09:34  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Slf4j
public class BussinessCglib implements MethodInterceptor {


    private Object obj;

    public Object getInstance(Object obj){
        this.obj = obj;
        Enhancer enhancer = new Enhancer();
        //继承被代理类
        enhancer.setSuperclass(obj.getClass());
        //设置回调
        enhancer.setCallback(this);
        return enhancer.create();
    }


    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        log.info("cglib 方法增强开始");
        Object obj =  methodProxy.invokeSuper(o,objects);
        log.info("cglib 方法增强结束");
        return obj;
    }
}
