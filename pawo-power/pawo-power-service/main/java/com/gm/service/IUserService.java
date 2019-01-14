/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */

package com.gm.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.IService;
import com.gm.entity.SysUser;
import com.gm.po.UserPo;

import java.util.List;

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
public interface IUserService extends IService<SysUser> {

    /**
     * 通过缓存获取用户列表信息
     *
     * @return 用户列表信息
     */
    List<UserPo> listUserPoByCache();

    /**
     * 通过数据库获取用户列表信息
     *
     * @param current 当前页码
     * @param size 一页数据量
     * @param type 用户类型
     * @return 用户列表信息
     */
    IPage<UserPo> listUserPoByDateBase(Page<UserPo> page, String type );

}
