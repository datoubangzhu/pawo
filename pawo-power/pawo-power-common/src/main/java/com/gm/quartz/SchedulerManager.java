package com.gm.quartz;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明.
 * pawo-power All rights reserved.
 */


import org.quartz.CronScheduleBuilder;
import org.quartz.CronTrigger;
import org.quartz.Job;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.TriggerKey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-08 19:55  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Component
public class SchedulerManager {

    private final Scheduler scheduler;

    @Autowired
    public SchedulerManager(Scheduler scheduler) {
        this.scheduler = scheduler;
    }


    /**
     * 开始定时任务
     * @param jobName job名称
     * @param jobGroup job工作组
     * @throws SchedulerException
     */
    public void startJob(String cron,String jobName,String jobGroup,Class<? extends Job> jobClass
    ,JobBeanParam param) throws SchedulerException
    {

        JobKey jobKey = new JobKey(jobName,jobGroup);
        if(!scheduler.checkExists(jobKey))
        {
            scheduleJob(cron,scheduler,jobName,jobGroup,jobClass,param);
        }
    }

    /**
     * 移除定时任务
     * @param jobName job名称
     * @param jobGroup job 工作组
     * @throws SchedulerException
     */
    public void deleteJob(String jobName,String jobGroup) throws SchedulerException
    {
        JobKey jobKey= new JobKey(jobName,jobGroup);
        scheduler.deleteJob(jobKey);
    }
    /**
     * 暂停定时任务
     * @param jobName job名称
     * @param jobGroup   job工作组
     * @throws SchedulerException
     */
    public void pauseJob(String jobName,String jobGroup) throws SchedulerException
    {
        JobKey jobKey=new JobKey(jobName,jobGroup);
        scheduler.pauseJob(jobKey);
    }


    /**
     * 获取scheduler中job执行状态
     *
     * @return 结果
     * @throws SchedulerException 异常
     */
    public String getStatus(String jobName,String jobGroup) throws SchedulerException {
        TriggerKey triggerKey =  new TriggerKey(jobName, jobGroup);
        Trigger.TriggerState state = scheduler.getTriggerState(triggerKey);
        return state.toString();
    }




    /**
     * 动态创建Job
     * 此处的任务可以配置可以放到properties或者是放到数据库中
     * Trigger:name和group 目前和job的name、group一致，之后可以扩展归类
     * @param scheduler 参数
     * @throws SchedulerException 异常
     */
    private void scheduleJob(String cron, Scheduler scheduler, String jobName, String jobGroup, Class<? extends Job> jobClass
    ,JobBeanParam param) throws SchedulerException {
        /*
         *  此处可以先通过任务名查询数据库，如果数据库中存在该任务，更新任务的配置以及触发器
         *  如果此时数据库中没有查询到该任务，则按照下面的步骤新建一个任务，并配置初始化的参数，并将配置存到数据库中
         */
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("param",param);
//        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).usingJobData(jobDataMap)
//                .build();
        JobDetail jobDetail = JobBuilder.newJob(jobClass).withIdentity(jobName, jobGroup).usingJobData(jobDataMap)
                .build();
        // 根据corn表达式执行
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cron);
        CronTrigger cronTrigger = TriggerBuilder.newTrigger().withIdentity(jobName, jobGroup).withSchedule(scheduleBuilder).build();
        scheduler.scheduleJob(jobDetail,cronTrigger);
    }
}
