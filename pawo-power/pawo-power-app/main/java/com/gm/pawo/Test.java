package com.gm.pawo;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */



import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-12-15 09:18  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@RequestMapping("test")
@RestController
public class Test {
    @RequestMapping("test")
    public void test(){
        System.out.println("hello world");
    }
}
