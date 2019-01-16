/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明.
 * pawo-power All rights reserved.
 */

package com.gm.job;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-09 14:11  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */

import com.gm.quartz.JobBeanParam;
import com.gm.quartz.ScheduleRun;
import com.gm.quartz.SpringContextUtil;
import com.gm.service.IUserService;

import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-08 20:15  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Slf4j
public class JobExecute extends QuartzJobBean {


    /**
     * 创建线程池
     * 核心线程 10
     * 最大线程 100
     * 队列长度：默认长度，Integer 最大值
     * 线程拒绝策略，默认 AbortPolicy
     */
    @SuppressWarnings("unchecked")
    private static final ExecutorService JOB_EXECUTOR_POOL = new ThreadPoolExecutor(10, 100, 1, TimeUnit.MINUTES,
            new LinkedBlockingQueue(Integer.MAX_VALUE)
                , new ThreadFactory() {
                    @Override
                    public Thread newThread(Runnable r) {
                        return new Thread(r);
                    }
                });

    private ExecutorService service = Executors.newSingleThreadExecutor();

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        JobDataMap jobDataMap = jobDetail.getJobDataMap();
        JobBeanParam param = (JobBeanParam) jobDataMap.get("param");
        log.info("执行job，获取参数:  "+param.toString());
        final IUserService userService = SpringContextUtil.getBean(IUserService.class);
        userService.listUserPoByCache();
        log.info("job开始执行-----------");
       Future future =  JOB_EXECUTOR_POOL.submit(new ScheduleRun(param));
        try {
            future.get();
            log.info("执行job无异常---------------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }



}

