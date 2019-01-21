/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */

package com.gm.config.orika;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.io.Serializable;

import lombok.Data;


/**
 * @author gmzhao
 */
@Data
@ConfigurationProperties("pawo.orika")
public class OrikaProperties implements Serializable {
    private static final long serialVersionUID = 1330557993068852870L;


    /**
     * Whether to enable auto-configuration.
     */
    private boolean enabled = true;

    /**
     * Whether to use built-in converters (MapperFactoryBuilder#useBuiltinConverters(boolean)).
     * Follows Orika's behavior by default.
     */
    private Boolean useBuiltinConverters;

    /**
     * Whether to use auto-mapping (MapperFactoryBuilder#useAutoMapping(boolean)).
     * Follows Orika's behavior by default.
     */
    private Boolean useAutoMapping;

    /**
     * Whether to map null values (MapperFactoryBuilder#mapNulls(boolean)).
     * Follows Orika's behavior by default.
     */
    private Boolean mapNulls;

    /**
     * Whether to dump the current state of the mapping infrastructure objects
     * upon occurrence of an exception while mapping (MapperFactoryBuilder#dumpStateOnException(boolean)).
     * Follows Orika's behavior by default.
     */
    private Boolean dumpStateOnException;

    /**
     * Whether the class-map should be considered 'abstract' (MapperFactoryBuilder#favorExtension(boolean)).
     * Follows Orika's behavior by default.
     */
    private Boolean favorExtension;

    /**
     * Whether full field context should be captured (MapperFactoryBuilder#captureFieldContext(boolean)).
     * Follows Orika's behavior by default.
     */
    private Boolean captureFieldContext;
}
