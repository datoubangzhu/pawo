/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明.
 * pawo-power All rights reserved.
 */

package com.gm.quartz;

import java.io.Serializable;

import lombok.Data;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-09 15:55  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Data
public class JobBeanParam implements Serializable{

    private static final long serialVersionUID = 3909678492997751130L;


    private String userName;

    private String password;

    private String sex;

}
