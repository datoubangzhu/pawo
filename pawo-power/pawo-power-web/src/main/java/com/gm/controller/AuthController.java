package com.gm.controller;
/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明.
 * pawo-power All rights reserved.
 */


import com.baomidou.mybatisplus.plugins.Page;
import com.gm.config.exception.PawoError;
import com.gm.config.exception.PawoException;
import com.gm.dubbo.service.DubboService;
import com.gm.po.UserPo;
import com.gm.request.UserRequest;
import com.gm.service.IUserService;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

import javax.validation.Valid;


/**
 * <p> 登录接口 </p>
 *
 * <pre> Created: 2019-01-02 13:53  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Controller
@Validated
@RequestMapping("pawo/user")
public class AuthController {

    private final static String USER_KEY = "pawo:auth:user";

    private IUserService                         userService;
    private HashOperations<String,Object,Object> hashOperations;
    private DubboService                         dubboService;



    @Autowired
    public AuthController(RedisTemplate<String,Object> redisTemplate,IUserService userService,DubboService dubboService) {
        this.hashOperations = redisTemplate.opsForHash();
        this.userService = userService;
        this.dubboService=dubboService;
    }


    /**
     * 登录认证接口
     *
     * @param username 用户名
     * @param userPassword 用户密码
     * @return 返回信息
     */
    @GetMapping("login")
    public @ResponseBody ResponseEntity auth(@RequestParam("username")String username,
                                             @RequestParam("password")String userPassword){
        boolean valid = userService.login(username,userPassword);
        if(valid){
            return ResponseEntity.ok("登录成功");
        }else{
            throw new PawoException("用户信息错误", PawoError.AUTH_FAILURE.getCode());
        }
    }


    /**
     * 创建用户
     *
     * @param userRequest 用户信息
     * @return 创建结果
     */
    @PostMapping("create")
    public ResponseEntity create(@RequestBody @Valid UserRequest userRequest){
        List<UserRequest> userRequests = Lists.newArrayList(userRequest);
        userService.createUsers(userRequests);
        return ResponseEntity.ok("创建成功");
    }


    /**
     * 批量创建用户
     *
     * @param userRequest 用户信息
     * @return 创建结果
     */
    @PostMapping("create/list")
    public ResponseEntity create(@RequestBody @Valid List<UserRequest> userRequest){
        userService.createUsers(userRequest);
        return ResponseEntity.ok("创建成功");
    }


    /**
     * 查询用户列表信息
     *
     * @return 用户列表信息
     */
    @GetMapping("list")
    public @ResponseBody ResponseEntity list(){
        List<UserPo> userPoList = userService.listUserPoByCache();
        return ResponseEntity.ok(userPoList);
    }


    /**
     * 查询用户列表信息
     * @param page 第几页
     * @param pageSize 每页显示条数
     * @param type 用户类型，非必传
     * @return 用户列表信息
     */
    @GetMapping("list/page")
    public @ResponseBody ResponseEntity listPage(@RequestParam(value = "page",required = false) Integer page,
                                                 @RequestParam(value = "pageSize",required = false) Integer pageSize,
                                                 @RequestParam(value = "type",required = false) String type){
        Page<UserPo> userPoPage = new Page<>(page,pageSize);
        Page<UserPo> userPoIPage = userService.listUserPoByDateBase(userPoPage,type);
        return ResponseEntity.ok(userPoIPage);
    }


    /**
     * dubbo测试，从rpc获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("dubbo")
    public @ResponseBody ResponseEntity dubboTest(){
        String resulte = dubboService.getDubboUser();
        return ResponseEntity.ok(resulte);
    }
}
