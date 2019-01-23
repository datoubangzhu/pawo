/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. gmzhao Inc.
 * pawo-power All rights reserved.
 */

package com.constant.mapping;

import com.gm.config.orika.OrikaMapperFactoryConfigurer;

import org.springframework.stereotype.Component;

import ma.glasnost.orika.MapperFactory;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-23 22:50  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class SystemRegister implements OrikaMapperFactoryConfigurer{

    @Override
    public void configure(MapperFactory mapperFactory) {

    }
}
