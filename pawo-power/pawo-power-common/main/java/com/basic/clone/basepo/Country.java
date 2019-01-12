package com.basic.clone.basepo;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */


import java.util.List;

import lombok.Builder;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-11-21 19:58  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Builder
public class Country {
        public Integer       id;
        public Integer       parentId;
        public String        name;
        public List<Country> countries;

}
