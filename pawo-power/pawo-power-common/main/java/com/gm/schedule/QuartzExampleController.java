package com.gm.schedule;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */


import org.junit.Test;
import org.quartz.Job;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.quartz.Scheduler;

import java.util.Date;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-06 17:46  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@RestController("quartz")
public class QuartzExampleController {

    class HelloQuartz implements Job {
        @Override
        public void execute(JobExecutionContext context) throws JobExecutionException {
            JobDetail detail = context.getJobDetail();
            String name = detail.getJobDataMap().getString("name");
            System.out.println("say hello to " + name + " at " + new Date());
        }
    }




    @RequestMapping("time")
    public void test() throws SchedulerException, InterruptedException {
        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();

        //定义一个Trigger
        //定义name/group
        Trigger trigger = newTrigger().withIdentity("trigger1", "group1")
                .startNow()//一旦加入scheduler，立即生效
                //使用SimpleTrigger
                .withSchedule(simpleSchedule()
                        //每隔一秒执行一次
                        .withIntervalInSeconds(1)
                        //只执行一次
                        .withRepeatCount(0))
                .build();

        //定义一个JobDetail
        //定义Job类为HelloQuartz类，这是真正的执行逻辑所在
        JobDetail job = newJob(HelloQuartz.class)
                //定义name/group
                .withIdentity("job1", "group1")
                //定义属性
                .usingJobData("name", "quartz")
                .build();

        //加入这个调度
        scheduler.scheduleJob(job, trigger);

        //启动之
        scheduler.start();


    }
}
