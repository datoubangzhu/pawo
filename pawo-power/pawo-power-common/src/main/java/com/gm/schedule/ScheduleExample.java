package com.gm.schedule;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明.
 * pawo-power All rights reserved.
 */


import org.junit.Test;
import org.quartz.CronExpression;
import org.quartz.impl.calendar.CronCalendar;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import cn.hutool.cron.CronTimer;
import cn.hutool.cron.CronUtil;
import cn.hutool.cron.pattern.CronPattern;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-06 17:01  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Component
@Slf4j
//@EnableScheduling
public class ScheduleExample {


    /**
     * 定时任务每隔5秒执行一次
     */
//    @Scheduled(cron ="0/5 * *  * * ? " )
    public void execute(){
        String time = DateUtil.date().toString(DatePattern.NORM_DATETIME_MS_PATTERN);
        log.info("开始执行定时任务--------------，当前时刻"+time);
    }


    @Test
    public void test(){

    }

}
