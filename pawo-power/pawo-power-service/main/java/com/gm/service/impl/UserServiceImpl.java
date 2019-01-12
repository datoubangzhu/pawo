/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */

package com.gm.service.impl;

import com.google.common.collect.Lists;

import com.gm.po.UserPo;
import com.gm.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.stereotype.Component;

import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-09 14:03  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Component
@Slf4j
public class UserServiceImpl implements IUserService{


    private final static String USER_KEY = "pawo:auth:user";

    private HashOperations<String,Object,Object> hashOperations;

    @Autowired
    public UserServiceImpl(HashOperations<String, Object, Object> hashOperations) {
        this.hashOperations = hashOperations;
    }




    @Override
    public List<UserPo> listUserPo() {
        List<UserPo> userPos = Lists.newArrayList();
        List<Object> userPoList = hashOperations.values(USER_KEY);
        for(Object o:userPoList){

        }


        log.info("执行UserServiceImpl 中 listUserPo方法---------");
        return null;
    }
}
