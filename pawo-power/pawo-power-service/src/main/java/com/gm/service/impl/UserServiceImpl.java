/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明.
 * pawo-power All rights reserved.
 */

package com.gm.service.impl;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gm.config.exception.PawoError;
import com.gm.config.exception.PawoException;
import com.gm.entity.SysUser;
import com.gm.mapper.UserMapper;
import com.gm.request.UserRequest;
import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;

import com.gm.po.UserPo;
import com.gm.service.IUserService;

import com.google.common.collect.Maps;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

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
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper,SysUser> implements IUserService{


    private final static String USER_KEY = "pawo:auth:user";

    private HashOperations<String,Object,Object> hashOperations;
    private RedisTemplate<String,Object>   redisTemplate;

    @Autowired
    public UserServiceImpl(RedisTemplate<String,Object> redisTemplate) {
        this.hashOperations = redisTemplate.opsForHash();
        this.redisTemplate = redisTemplate;
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
    public Page<UserPo> listUserPoByDateBase(Page<UserPo> page ,String type) {
        List<UserPo> userPos = baseMapper.listByName(page,type);
        Page<UserPo> userPoPage = new Page<>();
        userPoPage.setRecords(userPos);
        userPoPage.setCurrent(page.getCurrent());
        userPoPage.setSize(page.getSize());
        userPoPage.setTotal(page.getTotal());
        return userPoPage;
    }

    @Override
    public boolean login(String username,String userPassword) {
        UserRequest user = (UserRequest)hashOperations.get(USER_KEY,username);
        String password = user.getPassword();
        BCrypt.checkpw(password,userPassword);
        return false;
    }

    @Override
    public boolean checkListExist(List<UserRequest> userRequests) {
        boolean result = false;
        List<Object> userList = hashOperations.values(USER_KEY);
        Map<String,Object> userMap = Maps.newHashMap();
        for(Object obj : userList){
            UserPo userPo = (UserPo) obj;
            userMap.put(userPo.getUsername(), userPo);
        }
        for(UserRequest userRequest:userRequests){
            String userName = userRequest.getUsername();
            Object obj = userMap.get(userName);
            if(obj!=null){
                result = true;
            }
        }
        return result;
    }

    private void createIntoCache(List<UserRequest> userRequests) {
        for(UserRequest userRequest :userRequests){
            //入库参数封装
            final String userName = userRequest.getUsername();
            final String password = userRequest.getPassword();
            final String hashPassWord = BCrypt.hashpw(password, BCrypt.gensalt());
            final Date createDate = userRequest.getDate();
            final Date currentDateTime = DateUtil.date();
            final Date date = MoreObjects.firstNonNull(createDate, currentDateTime);
            final String creatTime = DateUtil.format(date, DatePattern.PURE_DATE_PATTERN);

            UserPo userPo = new UserPo();
            userPo.setUsername(userRequest.getUsername());
            userPo.setPassword(hashPassWord);
            userPo.setType(userRequest.getType());
            userPo.setCreateTime(creatTime);
            hashOperations.put(USER_KEY, userName, userPo);
        }
    }

    @Override
    public void createUsers(List<UserRequest> userRequests) {
        final boolean exist = this.checkListExist(userRequests);
        if(exist){
            throw new PawoException("用户已存在!", PawoError.USER_CREATE_FAILURE.getCode());
        }
        this.createIntoCache(userRequests);
    }
}
