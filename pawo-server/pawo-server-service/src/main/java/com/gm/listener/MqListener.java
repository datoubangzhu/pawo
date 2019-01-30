/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-server All rights reserved.
 */

package com.gm.listener;

import com.gm.config.rabbitmq.PawoMqConstant;
import com.gm.order.ShoppingOrdersRequest;
import com.gm.service.IOrderService;
import com.rabbitmq.client.Channel;

import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

import lombok.extern.slf4j.Slf4j;


/**
 * <p> 订单消息监听处理 </p>
 *
 * <pre> Created: 2019-01-29 20:06  </pre>
 * <pre> Project: pawo-server  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Component
@Slf4j
@RabbitListener(containerFactory = "rabbitListenerContainerFactory")
public class MqListener {


    private final IOrderService orderService;

    @Autowired
    public MqListener(IOrderService orderService) {
        this.orderService = orderService;
    }

    @RabbitListener(queues = PawoMqConstant.ORDER_QUEUE)
    public void process(Object message,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        ShoppingOrdersRequest shoppingOrdersRequest = (ShoppingOrdersRequest)message;
        log.info("接收到队列消息，队列: "+PawoMqConstant.ORDER_QUEUE+"  "+shoppingOrdersRequest.toString());
        orderService.mqSubmit(shoppingOrdersRequest);
        channel.basicAck(tag,false);
    }
}