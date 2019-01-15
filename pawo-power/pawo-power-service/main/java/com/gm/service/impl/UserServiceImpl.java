/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */

package com.gm.service.impl;

import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gm.entity.SysUser;
import com.gm.mapper.UserMapper;
import com.gm.po.UserPo;
import com.gm.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
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
public class UserServiceImpl extends ServiceImpl<UserMapper,SysUser> implements IUserService{


    private final static String USER_KEY = "pawo:auth:user";

    private HashOperations<String,Object,Object> hashOperations;

    @Autowired
    public UserServiceImpl(RedisTemplate<String,Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
    }


    @Override
    public List<UserPo> listUserPoByCache() {
        List<UserPo> userPos = Lists.newArrayList();
        List<Object> userPoList = hashOperations.values(USER_KEY);
        for(Object o:userPoList){
            userPos.add((UserPo) o);
        }
        return userPos;
    }

    @Override
    public List<UserPo> listUserPoByDateBase() {
        return null;
    }
}
