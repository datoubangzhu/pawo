package com.basic.proxy;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. 赵贵明.
 * pawo-power All rights reserved.
 */


import com.basic.proxy.cglib.BussinessCglib;
import com.basic.proxy.impl.Bussiness;
import com.basic.proxy.staticproxy.StaticProxy;

import org.junit.Test;
import org.springframework.cglib.proxy.Enhancer;

/**
 * <p> 测试 </p>
 *
 * <pre> Created: 2018-12-26 10:23  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public class MainTest {

    /**
     * 静态代理测试，代理类与被代理类实现相同的接口
     */
    @Test
    public void test(){
        StaticProxy staticProxy = new StaticProxy(new BussinessImpl());
        staticProxy.buy();
        staticProxy.sell();
    }

    /**
     * jdk 动态代理测试
     */
    @Test
    public void main() {
        DynamicProxy inter = new DynamicProxy();
        Bussiness bussiness = inter.getProxy();
        //加上这句将会产生一个$Proxy0.class文件，这个文件即为动态生成的代理类文件
//        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true");
        bussiness.sell();
        bussiness.buy();
    }

    /**
     * cglib 动态代理测试
     */
    @Test
    public void cglibTest(){
        BussinessCglib bussinessCglib = new BussinessCglib();
        BussinessImpl bussiness = (BussinessImpl) bussinessCglib.getInstance(new BussinessImpl());
        bussiness.buy();
    }
}

