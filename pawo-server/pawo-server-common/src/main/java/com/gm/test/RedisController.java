/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-server All rights reserved.
 */

package com.gm.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.annotation.Resource;


/**
 * <p> </p>
 *
 * <pre> Created: 2018-12-14 10:58  </pre>
 * <pre> Project: pawo-server  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("redis")
public class RedisController {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    @RequestMapping("test")
    public void test(){
//        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("Aming","565656");
        ValueOperations<String, String> valueOperation = stringRedisTemplate.opsForValue();
        valueOperation.set("hero","7878787878");
    }
}
