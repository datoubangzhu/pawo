package com.gm;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-11-19 17:40  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@SpringBootApplication
@EnableScheduling
public class PawoApplication {

    public static void main(String[] args){
        SpringApplication.run(PawoApplication.class,args);
    }
}
