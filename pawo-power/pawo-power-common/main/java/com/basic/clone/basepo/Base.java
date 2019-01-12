package com.basic.clone.basepo;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */


import lombok.Builder;

/**
 * author: gmzhao 20181122 10:37
 *
 * @author Administrator
 */
@Builder
public class Base implements Cloneable {

    public String username;
    public String password;
    public Param  param;

    @Override
    public Base clone() throws CloneNotSupportedException {
        Base base = (Base) super.clone();
//        base.param = param.clone();
        return base;
    }
}