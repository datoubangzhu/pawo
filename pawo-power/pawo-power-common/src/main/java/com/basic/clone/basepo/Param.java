package com.basic.clone.basepo;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. 赵贵明.
 * pawo-power All rights reserved.
 */


import lombok.Data;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-11-22 11:29  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class Param implements Cloneable{

    public String code;

    @Override
    public Param clone() throws CloneNotSupportedException {
        return  (Param) super.clone();

    }
}
