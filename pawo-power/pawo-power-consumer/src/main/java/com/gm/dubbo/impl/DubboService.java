package com.gm.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Reference;

import org.springframework.stereotype.Component;

import com.gm.user.DubboUserPo;
import com.gm.user.IDubboUserService;

/**
 * <p> dubbo 消费类</p>
 *
 * <pre> Created: 2019-01-15 22:56  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class DubboService {

    @Reference
    IDubboUserService dubboUserService;


    /**
     * 调用 dubbo 服务service
     */
    public void getDubboUser() {
        DubboUserPo dubboUserPo = dubboUserService.getDubboUser();
    }
}
