package com.basic.proxy.static_proxy;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */


import com.basic.proxy.BussinessImpl;
import com.basic.proxy.impl.Bussiness;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-12-26 11:04  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public class StaticProxy implements Bussiness {

    /**
     * 静态代理对象
     */
    private Bussiness object;

    public StaticProxy(BussinessImpl object) {
        this.object = object;
    }

    /**
     * 卖
     */
    @Override
    public void sell() {
        object.sell();
    }

    /**
     *  买
     */
    @Override
    public void buy() {
        object.buy();
    }

}
