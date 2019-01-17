package com.basic.proxy;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. 赵贵明.
 * pawo-power All rights reserved.
 */


import com.basic.proxy.impl.Bussiness;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-12-26 11:35  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public class BussinessImpl implements Bussiness {

    @Override
    public void sell() {
        System.out.println("sell---------------------");
    }

    @Override
    public void buy() {
        System.out.println("buy----------------------");
    }
}
