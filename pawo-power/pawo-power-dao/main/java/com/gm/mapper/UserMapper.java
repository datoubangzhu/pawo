/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */

package com.gm.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.gm.entity.SysUser;
import com.gm.po.UserPo;
import org.springframework.data.repository.query.Param;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-12 17:47  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public interface UserMapper extends BaseMapper<SysUser> {


    /**
     * 根据username查找用户信息
     *
     * @param page 分页信息
     * @param type 类型
     * @return 用户信息
     */
    IPage<UserPo> listByName(Page page,
                             @Param("type") String type);
}
