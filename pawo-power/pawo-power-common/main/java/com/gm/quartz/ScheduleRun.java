/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */

package com.gm.quartz;


import lombok.extern.slf4j.Slf4j;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-09 15:23  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Slf4j
public class ScheduleRun implements Runnable{

    private JobBeanParam jobBeanParam;

    public ScheduleRun(JobBeanParam jobBeanParam) {
        this.jobBeanParam = jobBeanParam;
    }

    @Override
    public void run() {
        int s = 1/0;
        String userName = jobBeanParam.getUserName();
        log.info("执行job方法，线程名称："+Thread.currentThread().getName()+"   参数名："+userName);
    }
}
