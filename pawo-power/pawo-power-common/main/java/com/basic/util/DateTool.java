/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */

package com.basic.util;

import java.util.Date;
import com.google.common.collect.Maps;

import org.junit.experimental.theories.DataPoint;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-10 10:57  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Slf4j
public class DateTool {




    /**
     * @param date 日期，格式 yyyy-MM-dd HH:mm:ss
     * @return corn 表达式
     */
    public static String getCornSeconds(Date date){
        Date dateCorn  = DateTime.of(date.toString(), DatePattern.NORM_DATETIME_PATTERN);
        return DateUtil.format(dateCorn,"ss mm HH dd MM ? yyyy");
    }

}
