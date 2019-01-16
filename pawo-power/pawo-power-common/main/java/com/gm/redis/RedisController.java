package com.gm.redis;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. 赵贵明.
 * pawo-power All rights reserved.
 */



import com.google.common.collect.Lists;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-12-10 08:52  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("redis")
@Slf4j
public class RedisController {


    private final HashOperations<String, Object, Object> hashOperations;
    private final ValueOperations<String, Object>        valueOperations;
    private final ListOperations<String, Object>         listOperations;


    @Autowired
    public RedisController(RedisTemplate<String, Object> redisTemplate) {
        this.valueOperations = redisTemplate.opsForValue();
        this.hashOperations = redisTemplate.opsForHash();
        this.listOperations = redisTemplate.opsForList();
    }
//
//    @Resource
//    private RedisTemplate<String, Object> redisTemplate;



//
//    @GetMapping("value/test")
//    public void valueTest() {
//        ValueOperations<String,Object> valueOperations = redisTemplate.opsForValue();
//        valueOperations.set("baby00001", "helloworld");
//        if (log.isDebugEnabled()) {
//            log.debug("name's value is {} ", valueOperations.get("name"));
//        }
//    }


    @GetMapping("hash/test")
    public void hashTest() {
        valueOperations.get("seee");
        hashOperations.put("com:gm", "name", "helloworld");
        if (log.isDebugEnabled()) {
            log.debug("com:gm's , name's value is {} ", hashOperations.get("com:gm", "name"));
        }
    }


    @GetMapping("list/test")
    public void listTest() {

        List<String> list = new ArrayList<>();
        list.add("list1");
        list.add("list2");
        list.add("list3");
        list.add("list4");
        listOperations.leftPushAll("listRedis", list);
        if (log.isDebugEnabled()) {
            log.debug("com:gm's , name's value is {} ", hashOperations.get("com:gm", "name"));
        }
    }


}
