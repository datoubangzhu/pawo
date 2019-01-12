package com.basic.proxy;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */


import com.basic.proxy.impl.Bussiness;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <p> 代理类 </p>
 *
 * <pre> Created: 2018-12-26 10:22  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public class DynamicProxy{
    /**
     * obj为委托类对象
     */
    private Bussiness bussiness = new BussinessImpl();


    public Bussiness getProxy(){
        return (Bussiness)Proxy.newProxyInstance(DynamicProxy.class.getClassLoader(), bussiness.getClass().getInterfaces(), new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println("before");
                Object object = method.invoke(bussiness,args);
                System.out.println("after");
                return object;
            }
        });
    }
}