package com.gm.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gm.util.MyDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.Jackson2ObjectMapperBuilder;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.text.DateFormat;
import java.util.SimpleTimeZone;
import java.util.TimeZone;

@Configuration
public class WebConfig {

    private final Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder;

    @Autowired
    public WebConfig(Jackson2ObjectMapperBuilder jackson2ObjectMapperBuilder) {
        this.jackson2ObjectMapperBuilder = jackson2ObjectMapperBuilder;
    }

    @Bean
    public MappingJackson2HttpMessageConverter MappingJsonpHttpMessageConverter() {

        ObjectMapper mapper = jackson2ObjectMapperBuilder.build();

        // ObjectMapper为了保障线程安全性，里面的配置类都是一个不可变的对象
        // 所以这里的setDateFormat的内部原理其实是创建了一个新的配置类
        DateFormat dateFormat = mapper.getDateFormat();
        mapper.setDateFormat(new MyDateFormat(dateFormat));
        //TODO 为何时区设置失败，设置后启动会报错呢
//        mapper.setTimeZone( TimeZone.getTimeZone("GMT+8"));
        return new MappingJackson2HttpMessageConverter(mapper);
    }
}
