package com.gm.schedule;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明.
 * pawo-power All rights reserved.
 */


import org.junit.Test;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-06 18:04  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Slf4j
@RestController("schedule")
public class TimeExampleController {

//    @RequestMapping("time")
//    public void test(){
//        Timer time = new Timer();
//        TimerTask timerTask = new TimerTask() {
//            @Override
//            public void run() {
//                log.info("Time  定时任务----");
//            }
//        };
//        Date date = DateTime.of("2019-01-06 18:12:00", DatePattern.NORM_DATETIME_PATTERN);
//        time.schedule(timerTask,date);
//    }

    @GetMapping("/executor")
    public String scheduledExecutorService() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
        final Date date = DateTime.of("2019-01-06 18:12:00", DatePattern.NORM_DATETIME_PATTERN);
        long delay = DateUtil.between(DateTime.now(),date, DateUnit.SECOND);
        //指定当前日期后的某时刻进行
        service.schedule(new Runnable() {
            @Override
            public void run() {
                log.info("ScheduleExecutorService 定时任务，只执行一次，时间："+ date);
            }
        },delay,TimeUnit.SECONDS);

        log.info("ScheduledExecutorService定时任务启动：" + new Date());
        return "ScheduledExecutorService!";
    }

    public TaskExecutor getThreadPool(){
        return new SyncTaskExecutor();
    }


}
