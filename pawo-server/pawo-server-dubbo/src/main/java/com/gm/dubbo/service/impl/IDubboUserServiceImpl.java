package com.gm.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.gm.service.IDubboUserService;
import com.gm.user.DubboUserPo;



import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;


/**
 * dubbo service服务
 *
 * @author gmzhao
 */
@Service
@Slf4j
@Component
public class IDubboUserServiceImpl implements IDubboUserService {

    @Override
    public DubboUserPo getDubboUser() {
        DubboUserPo userPo = new DubboUserPo();
        userPo.setUsername("aming");
        userPo.setPassword("123456");

        log.info("dubbo Service测试--------------------------------");

        return userPo;
    }
}
