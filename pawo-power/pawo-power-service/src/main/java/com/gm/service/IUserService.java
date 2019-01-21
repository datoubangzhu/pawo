/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明.
 * pawo-power All rights reserved.
 */

package com.gm.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.gm.entity.SysUser;
import com.gm.po.UserPo;
import com.gm.request.UserRequest;

import java.util.List;

/**
 * <p> 用户相关service </p>
 *
 * <pre> Created: 2019-01-09 14:03  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public interface IUserService extends IService<SysUser> {


    /**
     * 检查用户信息是否存在
     *
     * @param userRequests 用户请求信息
     * @return 用户是否存在
     */
    boolean checkListExist(List<UserRequest> userRequests);


    /**
     * 批量创建用户
     *
     * @param userRequest 用户信息
     */
    void createUsers(List<UserRequest> userRequest);

    /**
     * 通过缓存获取用户列表信息
     *
     * @return 用户列表信息
     */
    List<UserPo> listUserPoByCache();

    /**
     * 通过数据库获取用户列表信息
     *
     * @param page 分页信息
     * @param type 用户类型
     * @return 用户列表信息
     */
    Page<UserPo> listUserPoByDateBase(Page<UserPo> page, String type );


    /**
     * 用户请求信息接口
     *
     * @param userRequest 用户信息
     * @return 是否登录成功
     */
    boolean login(String username,String userPassword);
}
