package com.basic.proxy.staticproxy;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. 赵贵明.
 * pawo-power All rights reserved.
 */


import com.basic.proxy.BussinessImpl;
import com.basic.proxy.impl.Bussiness;

import lombok.extern.slf4j.Slf4j;

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
@Slf4j
public class StaticProxy implements Bussiness {

    /**
     * 静态代理对象,不同于装饰器：在编译时期就确定了要代理的具体实现类。
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
        log.info("虽然涨价了，但是我还是原价卖给你！");
        object.sell();
    }

    /**
     *  买
     */
    @Override
    public void buy() {
        log.info("虽然便宜了，但是我还是按照定价买你的");
        object.buy();
    }

}
