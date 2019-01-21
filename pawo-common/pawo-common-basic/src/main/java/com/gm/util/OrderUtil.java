/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. gmzhao Inc.
 * pawo-common All rights reserved.
 */

package com.gm.util;

import java.util.UUID;

import ch.qos.logback.core.util.DatePatternToRegexUtil;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-21 15:11  </pre>
 * <pre> Project: pawo-common  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public class OrderUtil {

    /**
     * 获取唯一订单号
     *
     * @return 订单号
     */
    public static String getOrderSn(){
        int hashcode =   UUID.randomUUID().toString().hashCode();
        if(hashcode<0){
            hashcode = - hashcode;
        }
        String date = DateUtil.date().toString(DatePattern.PURE_DATETIME_FORMAT);
        return date + String.valueOf(hashcode);
    }
}
