/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-server All rights reserved.
 */

package com.gm.config;

import com.baomidou.mybatisplus.incrementer.IKeyGenerator;
import com.baomidou.mybatisplus.incrementer.OracleKeyGenerator;
import com.baomidou.mybatisplus.mapper.ISqlInjector;
import com.baomidou.mybatisplus.mapper.LogicSqlInjector;
import com.baomidou.mybatisplus.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.spring.boot.starter.MybatisPlusProperties;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-16 22:35  </pre>
 * <pre> Project: pawo-server  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Configuration
@EnableConfigurationProperties(MybatisPlusProperties.class)
@MapperScan("com.gm.mapper")
public class MybatisPlusConfig {

    /**
     * 分页插件，自动识别数据库类型
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

    /**
     * 注入主键生成器，此处为Oracle主键生成器来实现
     */
    @Bean
    public IKeyGenerator keyGenerator() {
        return new OracleKeyGenerator();
    }

    /**
     * Sql 注入器。此处配置逻辑删除，与@TableLogic配合使用
     */
    @Bean
    public ISqlInjector sqlInjector() {
        return new LogicSqlInjector();
    }
}
