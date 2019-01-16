/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. 赵贵明.
 * pawo-server All rights reserved.
 */
package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <p> 服务模块启动类 </p>
 *
 * <pre> Created: 2018-12-14 09:14  </pre>
 * <pre> Project: pawo-server  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@SpringBootApplication
public class PawoServerApplication {
    public static void main(String[] args){
        SpringApplication.run(PawoServerApplication.class,args);
    }
}
