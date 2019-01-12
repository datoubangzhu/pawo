package com.basic.clone.algorithmb;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */


import com.basic.clone.basepo.Country;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;


import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.util.ObjectUtil;


/**
 * <p> </p>
 *
 * <pre> Created: 2018-11-21 19:34  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public class Tree {


    /**
     * 中国0 北京0-1 上海0-2 重庆0-3 安徽0-4 朝阳区1-5 丰台区1-6 黄浦区2-7 嘉定区2-8 沙坪坝3-9 合肥4-10 芜湖 4-11
     */

    @Test
    public void test(){
        List<Country> countryList = new ArrayList<>();
        countryList.add(Country.builder().id(0).parentId(null).name("中国").build());
        countryList.add(Country.builder().id(1).parentId(0).name("北京").build());
        countryList.add(Country.builder().id(2).parentId(0).name("上海").build());
        countryList.add(Country.builder().id(3).parentId(0).name("重庆").build());
        countryList.add(Country.builder().id(4).parentId(0).name("安徽").build());
        countryList.add(Country.builder().id(5).parentId(1).name("朝阳区").build());
        countryList.add(Country.builder().id(6).parentId(1).name("丰台区").build());
        countryList.add(Country.builder().id(7).parentId(2).name("黄浦区").build());
        countryList.add(Country.builder().id(8).parentId(2).name("嘉定区").build());
        countryList.add(Country.builder().id(9).parentId(3).name("沙坪坝").build());
        countryList.add(Country.builder().id(10).parentId(4).name("合肥").build());
        countryList.add(Country.builder().id(11).parentId(4).name("芜湖").build());
        List<Country> valueList = overwriteToTree(countryList);
    }

    /**
     * 构建树形结构
     *
     * @param countries 参数列表
     * @return 树形结构
     */
    private List<Country> overwriteToTree(List<Country> countries) {
        List<Country> valueList = new ArrayList<>();
        for (Country country : countries) {
            if(CollectionUtil.isEmpty(country.countries)){
                country.countries = new ArrayList<>();
            }
            for (Country param : countries) {
                Integer countryId = country.id;
                Integer parentId = param.parentId;
                if (ObjectUtil.isNotNull(parentId) && countryId.equals(parentId)) {
                    country.countries.add(param);
                }
            }
            //只要最高级别,没有父级别即为最高级别
            if (ObjectUtil.isNull(country.parentId)){
                valueList.add(country);
            }
        }
        System.out.println("============================");
        return valueList;
    }

}
