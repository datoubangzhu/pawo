/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. gmzhao Inc.
 * pawo-power All rights reserved.
 */

package com.gm.config.orika;

import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory.MapperFactoryBuilder;

/**
 * @author gmzhao
 */
@Slf4j
@ConditionalOnProperty(name = "pawo.orika.enabled",matchIfMissing = true)
@EnableConfigurationProperties(OrikaProperties.class)
@Configuration
public class OrikaAutoConfiguration {

    /**
     * The configuration properties for Orika.
     */
    private final OrikaProperties orikaProperties;

    /**
     * The configurers for {@link MapperFactoryBuilder}.
     */
    private final List<OrikaMapperFactoryBuilderConfigurer> orikaMapperFactoryBuilderConfigurers;

    /**
     * The configurers for {@link MapperFactory}.
     */
    private final List<OrikaMapperFactoryConfigurer> orikaMapperFactoryConfigurers;

    @Autowired
    public OrikaAutoConfiguration(
            OrikaProperties orikaProperties,
            ObjectProvider<List<OrikaMapperFactoryBuilderConfigurer>> orikaMapperFactoryBuilderConfigurers,
            ObjectProvider<List<OrikaMapperFactoryConfigurer>> orikaMapperFactoryConfigurers
    ) {
        this.orikaProperties = orikaProperties;
        this.orikaMapperFactoryBuilderConfigurers = orikaMapperFactoryBuilderConfigurers.getIfAvailable();
        this.orikaMapperFactoryConfigurers = orikaMapperFactoryConfigurers.getIfAvailable();
    }


    /**
     * Creates a {@link MapperFactoryBuilder}.
     *
     * @return a {@link MapperFactoryBuilder}.
     */
    @ConditionalOnMissingBean
    @Bean
    public MapperFactoryBuilder orikaMapperFactoryBuilder() {
        DefaultMapperFactory.Builder orikaMapperFactoryBuilder = new DefaultMapperFactory.Builder();
        if (orikaProperties.getUseBuiltinConverters() != null) {
            orikaMapperFactoryBuilder.useBuiltinConverters(orikaProperties.getUseBuiltinConverters());
        }
        if (orikaProperties.getUseAutoMapping() != null) {
            orikaMapperFactoryBuilder.useAutoMapping(orikaProperties.getUseAutoMapping());
        }
        if (orikaProperties.getMapNulls() != null) {
            orikaMapperFactoryBuilder.mapNulls(orikaProperties.getMapNulls());
        }
        if (orikaProperties.getDumpStateOnException() != null) {
            orikaMapperFactoryBuilder.dumpStateOnException(orikaProperties.getDumpStateOnException());
        }
        if (orikaProperties.getFavorExtension() != null) {
            orikaMapperFactoryBuilder.favorExtension(orikaProperties.getFavorExtension());
        }
        if (orikaProperties.getCaptureFieldContext() != null) {
            orikaMapperFactoryBuilder.captureFieldContext(orikaProperties.getCaptureFieldContext());
        }
        if (CollectionUtil.isNotEmpty(orikaMapperFactoryBuilderConfigurers)) {
            for (OrikaMapperFactoryBuilderConfigurer orikaMapperFactoryBuilderConfigurer : orikaMapperFactoryBuilderConfigurers) {
                if (log.isDebugEnabled()) {
                    log.debug("Orika::: Mapper Factory Builder Configurer is : [{}]", orikaMapperFactoryBuilderConfigurer.getClass());
                }
                orikaMapperFactoryBuilderConfigurer.configure(orikaMapperFactoryBuilder);
            }
        }
        if (log.isDebugEnabled()) {
            log.debug("Orika::: Created a MapperFactoryBuilder: [{}]", orikaMapperFactoryBuilder);
        }
        return orikaMapperFactoryBuilder;
    }

    /**
     * Creates a {@link MapperFactory}.
     *
     * @param orikaMapperFactoryBuilder the {@link MapperFactoryBuilder}.
     * @return a {@link MapperFactory}.
     */
    @ConditionalOnMissingBean
    @Bean
    public MapperFactory orikaMapperFactory(MapperFactoryBuilder<?, ?> orikaMapperFactoryBuilder) {
        MapperFactory orikaMapperFactory = orikaMapperFactoryBuilder.build();
        if (CollectionUtil.isNotEmpty(orikaMapperFactoryConfigurers)) {
            for (OrikaMapperFactoryConfigurer orikaMapperFactoryConfigurer : orikaMapperFactoryConfigurers) {
                if (log.isDebugEnabled()) {
                    log.debug("Orika::: Mapper Factory Configurer is : [{}]", orikaMapperFactoryConfigurer.getClass());
                }
                orikaMapperFactoryConfigurer.configure(orikaMapperFactory);
            }
        }
        log.debug("Created a MapperFactory: [{}]", orikaMapperFactory);
        return orikaMapperFactory;
    }

    /**
     * Creates a {@link MapperFacade}.
     *
     * @param orikaMapperFactory the {@link MapperFactory}.
     * @return a {@link MapperFacade}.
     */
    @ConditionalOnMissingBean
    @Bean
    public MapperFacade orikaMapperFacade(MapperFactory orikaMapperFactory) {
        MapperFacade orikaMapperFacade = orikaMapperFactory.getMapperFacade();
        if (log.isDebugEnabled()) {
            log.debug("Orika::: Created a MapperFacade: [{}]", orikaMapperFacade);
        }
        return orikaMapperFacade;
    }
}
