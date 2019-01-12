package com.basic.clone.algorithmb;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */


import com.basic.clone.basepo.Base;
import com.basic.clone.basepo.Param;

import org.junit.Test;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-11-22 10:29  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public class DeepAndShallowCloneTest {


    @Test
    public void test() throws CloneNotSupportedException {
        Param param = new Param();
        param.setCode("paramcode");

        Base base = Base.builder().username("aaaaaa").password("123456").param(param).build();
        Base baseClone = base.clone();

        param.setCode("paramCodeTwo");



        System.out.println("username: "+baseClone.username+"   password: "+baseClone.password);
        System.out.println("param code:   "+baseClone.param.code);


    }
}























