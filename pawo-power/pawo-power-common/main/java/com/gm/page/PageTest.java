package com.gm.page;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */


import com.gm.page.util.PageRequest;
import com.gm.page.util.PageResponse;
import com.gm.page.util.PageUtil;

import org.junit.Test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * <p> </p>
 *
 * <pre> Created: 2018-11-26 16:54  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public class PageTest {
    class Po implements Serializable{
        private static final long serialVersionUID = -6573807474091622820L;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        int value;
    }

    @Test
    public void test(){
        List<Po> poList = new ArrayList<>();
        for(int i= 0;i<26;i++) {
            Po po = new Po();
            po.setValue(i);
            poList.add(po);
        }

        PageRequest pageRequest = new PageRequest();
        PageResponse<Po> poPageResponse = PageUtil.noSqlHandlePage(pageRequest,poList);
        System.out.println(poPageResponse);
    }
}
