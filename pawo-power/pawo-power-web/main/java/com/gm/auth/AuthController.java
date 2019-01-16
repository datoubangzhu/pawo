package com.gm.auth;
/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. gmzhao.
 * pawo-power All rights reserved.
 */



import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.plugins.Page;
import com.gm.dubbo.impl.DubboService;
import com.gm.po.UserPo;
import com.gm.request.UserRequest;

import com.gm.service.IUserService;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.DateFormat;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;


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
@RequestMapping("pawo/user")
public class AuthController {

    private IUserService                         userService;
    private HashOperations<String,Object,Object> hashOperations;
    private DubboService                         dubboService;

    private final static String USER_KEY = "pawo:auth:user";

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
        UserRequest user = (UserRequest)hashOperations.get(USER_KEY,username);
        String password = user.getPassword();
        boolean valid = BCrypt.checkpw(password,userPassword);
        if(valid){
            return ResponseEntity.ok("登录成功");
        }else{
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("登录失败");
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
        String userName = userRequest.getUsername();
        Object object = hashOperations.get(USER_KEY,userName);
        if(object!=null){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("用户已存在");
        }
        String password = userRequest.getPassword();
        String hashPassWord = BCrypt.hashpw(password,BCrypt.gensalt());
        final Date createDate = userRequest.getDate();
        final Date currentDateTime = DateUtil.date();
        final Date date =  MoreObjects.firstNonNull(createDate,currentDateTime);
        final String creatTime = DateUtil.format(date,DatePattern.PURE_DATE_PATTERN);

        UserPo userPo = new UserPo();
        userPo.setUsername(userRequest.getUsername());
        userPo.setPassword(hashPassWord);
        userPo.setType(userRequest.getType());
        userPo.setCreateTime(creatTime);
        hashOperations.put(USER_KEY,userName,userPo);
        return ResponseEntity.ok("创建成功");
    }


    /**
     * 查询用户列表信息
     *
     * @return 用户列表信息
     */
    @GetMapping("list")
    public @ResponseBody ResponseEntity list(){
        List<Object> userPoList = hashOperations.values(USER_KEY);
        List<UserPo> userPos = Lists.newArrayList();
        userPoList.forEach(user-> userPos.add((UserPo) user));
        return ResponseEntity.ok(userPos);
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


    @GetMapping("dubbo")
    public @ResponseBody ResponseEntity dubboTest(){
        String resulte = dubboService.getDubboUser();
        return ResponseEntity.ok(resulte);
    }
}
