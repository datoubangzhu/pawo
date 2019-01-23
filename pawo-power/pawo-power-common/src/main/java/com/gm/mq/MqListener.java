package com.gm.mq;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. 赵贵明.
 * pawo-power All rights reserved.
 */


import com.rabbitmq.client.Channel;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-12-02 13:58  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Component
@RabbitListener(queues = "hello",containerFactory = "rabbitListenerContainerFactory")
public class MqListener {

    @RabbitListener(queues = "queue")
    public void process(String hello) {
        System.out.println("Receiver  : " + hello);
    }

    @RabbitListener(queues = "topicQueue")
    public void exchangeProcess(String hello) {
        System.out.println("TopicQueue Receiver  : " + hello);
    }

    @RabbitListener(queues = "headerQueue")
    public void headersProcess(byte[] hello, Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) throws IOException {
        System.out.println("HeadersQueue Receiver  : " + new String(hello));
        channel.basicAck(tag,false);
    }
}
