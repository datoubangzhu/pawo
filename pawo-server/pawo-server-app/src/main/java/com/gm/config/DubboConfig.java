/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明.
 * pawo-server All rights reserved.
 */

package com.gm.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.MonitorConfig;
import com.alibaba.dubbo.config.ProtocolConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> dubbo 配置 </p>
 *
 * <pre> Created: 2019-01-16 11:16  </pre>
 * <pre> Project: pawo-server  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Configuration
@DubboComponentScan(basePackages = "com.gm.dubbo.service.impl")
public class DubboConfig {


    @Bean
    public ApplicationConfig applicationConfig() {
        //服务名称
        ApplicationConfig applicationConfig = new ApplicationConfig();
        applicationConfig.setName("provider-power");
        return applicationConfig;
    }

    @Bean
    public RegistryConfig registry(){
        RegistryConfig registryConfig = new RegistryConfig();
        registryConfig.setAddress("192.168.3.140:2181");
        registryConfig.setProtocol("zookeeper");
        return registryConfig;
    }


    @Bean
    public ProtocolConfig protocolConfig(){
        ProtocolConfig protocolConfig = new ProtocolConfig();
        protocolConfig.setName("dubbo");
        protocolConfig.setPort(20883);
        return protocolConfig;
    }

    @Bean
    public MonitorConfig monitorConfig(){
        //#dubbo 协议
        MonitorConfig monitorConfig = new MonitorConfig();
        monitorConfig.setProtocol("registry");
        return monitorConfig;
    }


}
