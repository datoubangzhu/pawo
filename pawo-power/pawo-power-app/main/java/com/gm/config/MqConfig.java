package com.gm.config;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */






import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;

import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * <p> </p>
 *
 * <pre> Created: 2018-12-02 16:32  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Configuration
public class MqConfig {


    @Bean
    public RabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        //开启手动 ack
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }


    /**
     * ****************************************************************************
     * Direct模式
     *
     *
     */
    @Bean
    public Queue queue() {
        return new Queue("queue");
    }


    /**
     * ****************************************************************************
     * Topic模式
     *
     */
    @Bean
    public Queue testQueue() {
        return new Queue("testQueue");
    }


    @Bean
    public TopicExchange testExchange() {
        return new TopicExchange("testExchange");
    }



    @Bean
    Binding bindingExchangeMessageTest(@Qualifier("testQueue") Queue queueMessage, @Qualifier("testExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("testKey");
    }

    /**
     * ****************************************************************************
     * Headers 模式
     *
     */

    @Bean
    public Queue headerQueue() {
        return new Queue("headerQueue");
    }

    @Bean
    public HeadersExchange headersExchange(){
        return new HeadersExchange("headerExchange");
    }

    @Bean
    Binding connectHeaderExchange(){
        return BindingBuilder.bind(headerQueue()).to(headersExchange()).where("token").matches("50448837");
    }


    /**
     * ******************************************************************************
     * Fanout 模式
     *
     */

    @Bean
    public FanoutExchange fanoutExchange(){
        return new FanoutExchange("fanout");
    }






//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate template = new RabbitTemplate(connectionFactory);
//        template.setMessageConverter(new Jackson2JsonMessageConverter());
//        return template;
//    }
//
//    @Bean
//    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
//        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
//        factory.setConnectionFactory(connectionFactory);
//        factory.setMessageConverter(new Jackson2JsonMessageConverter());
//        return factory;
//    }


}
