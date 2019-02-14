/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-power All rights reserved.
 */

package com.basic.util;

import org.apache.commons.lang.StringUtils;

import sun.swing.StringUIClientPropertyKey;

import java.util.UUID;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import ma.glasnost.orika.impl.util.StringUtil;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-02-14 15:36  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public class IIDUtil {

    public static double getIid(){
        final String left = DateUtil.date().toString(DatePattern.PURE_DATETIME_PATTERN);
        int hashcode = UUID.randomUUID().toString().hashCode();
        if (hashcode < 0) {
            hashcode = -hashcode;
        }
        return Double.valueOf(left)+hashcode;
    }
}
