package com.gm.mq;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */



import com.rabbitmq.client.Connection;
import com.rabbitmq.client.Channel;

import com.rabbitmq.client.ConnectionFactory;

import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

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
@RestController
@RequestMapping("rabbit")
public class MqProducterTest {



    @Autowired
    private RabbitTemplate rabbitTemplate;


    @GetMapping("test")
    public void testRabbit() throws IOException, TimeoutException {
        ConnectionFactory connectionFactory =new ConnectionFactory();
//        connectionFactory.setHost("10.136.198.140");
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/pawo_power");
        connectionFactory.setUsername("gmzhao");
        connectionFactory.setPassword("123456");
        //2.通过连接工厂创建连接       
        Connection connection =connectionFactory.newConnection();
        // 3.通过连接创建channel       
        Channel channel = connection.createChannel();
        // 4 通过channle发送数据       
//        for (int i=0;i< 5;i++) {
            String msg ="Hello RabbitMQ";
            channel.basicPublish("", "hello", null, msg.getBytes());
//        }
        // 5 关闭连接       
        channel.close();
        connection.close();
    }




    @GetMapping("amqp/test")
    public void testAMQP(){
        for(int i = 0 ;i < 10; i++) {
            rabbitTemplate.convertAndSend("queue", "queue  salijaljlsjlj");
        }
    }


    @GetMapping("direct/test")
    public void testDirect(){
        for(int i = 0 ;i < 10; i++) {
            rabbitTemplate.convertAndSend("queue", "queue  salijaljlsjlj");
        }
    }


    @GetMapping("topic/test")
    public void testTopic(){
            rabbitTemplate.convertAndSend("testExchange","testKey","***  helloworld TEST queue = key+exchange");
    }


    @GetMapping("headers/test")
    public void testHeaders(){

        MessagePostProcessor messagePostProcessor = new MessagePostProcessor() {
            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                message.getMessageProperties().getHeaders().put("headers","50448837");
                return message;
            }
        };
       //为啥这样不行 rabbitTemplate.convertAndSend("headerExchange","", (Object) messagePostProcessor);
        //为啥这样也不行 rabbitTemplate.convertAndSend("headerExchange", (Object) "s",messagePostProcessor);

        String msg = "Hello HeadersMsg";
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setHeader("token", "50448837");
        Message message = new Message(msg.getBytes(), messageProperties);
        for(int i =0;i<100;i++) {
            rabbitTemplate.convertAndSend("headerExchange", "",message);
        }


    }

}
