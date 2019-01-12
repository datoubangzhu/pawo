package com.gm.test;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */



import com.basic.util.DateTool;
import com.gm.job.JobExecute;
import com.gm.quartz.JobBeanParam;
import com.gm.quartz.SchedulerManager;

import org.quartz.SchedulerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-08 20:09  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@RestController
@RequestMapping("job")
public class JobController {

    final private SchedulerManager schedulerManager;

    @Autowired
    public JobController(SchedulerManager schedulerManager) {
        this.schedulerManager = schedulerManager;
    }

    @RequestMapping("test")
    public void test() throws SchedulerException {
        JobBeanParam param = new JobBeanParam();
        param.setUserName("hello");
        param.setPassword("123456");
        param.setSex("男");
        String cron = "0/5 * * * * ? ";
        Date date = DateTime.of("2019-01-10 11:48:00", DatePattern.NORM_DATETIME_PATTERN);
        schedulerManager.startJob(DateTool.getCornSeconds(date),"firstJob","demoJobGroup",JobExecute.class,param);
    }

}
