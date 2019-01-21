/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */

package com.gm.config.orika;

import ma.glasnost.orika.impl.DefaultMapperFactory.MapperFactoryBuilder;


/**
 * @author gmzhao
 */
public interface OrikaMapperFactoryBuilderConfigurer {


    /**
     * Configures the {@link MapperFactoryBuilder}.
     *
     * @param orikaMapperFactoryBuilder the {@link MapperFactoryBuilder}.
     */
    void configure(MapperFactoryBuilder<?, ?> orikaMapperFactoryBuilder);
}
