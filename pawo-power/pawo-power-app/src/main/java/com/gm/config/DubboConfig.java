package com.gm.config;

import com.alibaba.dubbo.config.ApplicationConfig;
import com.alibaba.dubbo.config.RegistryConfig;
import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-16 10:54  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */

@Configuration
@DubboComponentScan(basePackages = "com.gm.dubbo.service")
public class DubboConfig {


        @Bean
        public ApplicationConfig applicationConfig() {
            //服务名称
            ApplicationConfig applicationConfig = new ApplicationConfig();
            applicationConfig.setName("consumer-power");
            return applicationConfig;
        }


        @Bean
        public RegistryConfig registryConfig() {
            //#注册中心地址
            RegistryConfig registryConfig = new RegistryConfig();
            registryConfig.setAddress("192.168.3.140:2181");
            registryConfig.setProtocol("zookeeper");
            return registryConfig;
        }

//TODO 消费端不配置也可以，为什么
//    @Bean
//    public ProtocolConfig protocolConfig(){
//        ProtocolConfig protocolConfig = new ProtocolConfig();
//        protocolConfig.setName("dubbo");
//        protocolConfig.setPort(20883);
//        return protocolConfig;
//    }


//TODO 消费端不配置也可以，为什么
//        @Bean
//        public MonitorConfig monitorConfig(){
//            //#dubbo 协议
//            MonitorConfig monitorConfig = new MonitorConfig();
//            monitorConfig.setProtocol("registry");
//            return monitorConfig;
//        }

}
