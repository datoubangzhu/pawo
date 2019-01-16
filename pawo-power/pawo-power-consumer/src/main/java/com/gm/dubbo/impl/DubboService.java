package com.gm.dubbo.impl;

import com.alibaba.dubbo.config.annotation.Reference;
import com.gm.user.DubboUserPo;
import com.gm.user.service.IDubboUserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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

    private static final Logger LOGGER = LoggerFactory.getLogger(DubboService.class);

    @Reference
    private IDubboUserService dubboUserService;


    /**
     * 调用 dubbo 服务service
     */
    public String getDubboUser() {
        DubboUserPo dubboUserPo = dubboUserService.getDubboUser();
        final String string = dubboUserPo.toString();
        LOGGER.info("调用dubbo服务成功----------，返回结果 {}", string);
        return string;
    }
}
