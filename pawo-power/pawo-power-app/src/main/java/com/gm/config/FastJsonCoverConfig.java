/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. gmzhao Inc.
 * pawo-power All rights reserved.
 */

package com.gm.config;

import com.google.common.collect.Lists;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.ToStringSerializer;
/**
 *  use this jar will cause error in start
 * import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
 */
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;


import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import cn.hutool.core.date.DatePattern;


/**
 * <p>  </p>
 *
 * <pre> Created: 2019-01-21 11:05  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Configuration
@ConditionalOnClass({JSON.class})
@ConditionalOnWebApplication
@ConditionalOnMissingBean(FastJsonHttpMessageConverter.class)
public class FastJsonCoverConfig {


    @Bean
    @ConditionalOnClass({FastJsonHttpMessageConverter.class})
    public FastJsonHttpMessageConverter fastJsonHttpMessageConverter() {
        final FastJsonHttpMessageConverter converter = new FastJsonHttpMessageConverter();
        converter.setFastJsonConfig(fastjsonConfig());
        converter.setSupportedMediaTypes(getSupportedMediaType());

        return converter;
    }

    private List<MediaType> getSupportedMediaType() {

        List<MediaType> mediaTypes = Lists.newArrayList();
        mediaTypes.add(MediaType.APPLICATION_JSON_UTF8);
        return mediaTypes;
    }

    private FastJsonConfig fastjsonConfig() {


        FastJsonConfig fastJsonConfig = new FastJsonConfig();

        final SerializeConfig serializeConfig = SerializeConfig.globalInstance;
        serializeConfig.put(BigInteger.class, ToStringSerializer.instance);
        serializeConfig.put(BigDecimal.class, ToStringSerializer.instance);
        serializeConfig.put(Long.class, ToStringSerializer.instance);
        serializeConfig.put(Long.TYPE, ToStringSerializer.instance);
        fastJsonConfig.setSerializeConfig(serializeConfig);
        // @see https://my.oschina.net/u/2474629/blog/748752
        fastJsonConfig.setSerializerFeatures(
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.WriteNullNumberAsZero,
                SerializerFeature.WriteNullStringAsEmpty,
                SerializerFeature.WriteNullBooleanAsFalse,
                SerializerFeature.WriteDateUseDateFormat,
                // 关闭 fastjson的循环引用
                SerializerFeature.DisableCircularReferenceDetect,
                //设置WriteEnumUsingToString
                SerializerFeature.WriteEnumUsingToString,
                SerializerFeature.BrowserCompatible
        );
        fastJsonConfig.setDateFormat(DatePattern.NORM_DATETIME_PATTERN);
        return fastJsonConfig;
    }
}
