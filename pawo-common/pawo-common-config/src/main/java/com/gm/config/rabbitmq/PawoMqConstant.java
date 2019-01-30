/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-common All rights reserved.
 */

package com.gm.config.rabbitmq;

/**
 * <p> mq 队列，交换机常量信息 </p>
 *
 * <pre> Created: 2019-01-30 09:21  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public interface PawoMqConstant {

    /**
     * 声明订单队列
     */
    String ORDER_QUEUE = "pawo:order";
    /**
     * 声明订单队列交换机
     */
    String ORDER_EXCHANGE = "order:exchange";
    /**
     * 订单交换机队列匹配key值
     */
    String ORDER_KEY = "iknow.u:pawo";



}
