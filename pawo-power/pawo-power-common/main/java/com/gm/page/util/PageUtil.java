package com.gm.page.util;/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2018. haiyi Inc.
 * pawo-power All rights reserved.
 */


import com.gm.page.util.PageRequest;
import com.gm.page.util.PageResponse;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.hutool.core.collection.CollectionUtil;

/**
 * <p> 分页处理 </p>
 *
 * <pre> Created: 2018-11-26 15:22  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public final class PageUtil {
    /**
     * 免sql分页
     *
     * @param request 请求分页参数
     * @param list 待分页集合
     * @param <Po> 泛型
     * @return 分页结果
     */
    public static <Po extends Serializable> PageResponse<Po> noSqlHandlePage(PageRequest request, List<Po> list){
        int page = request.getPage();
        int size = request.getSize();
        if(CollectionUtil.isEmpty(list)){
            return PageResponse.<Po>builder()
                    .page(request.getPage())
                    .size(request.getSize())
                    .message("COLLECTION IS EMPTY!").build();
        }
        if(page<=0){
            return PageResponse.<Po>builder()
                    .page(request.getPage())
                    .size(request.getSize())
                    .message("PARAMTER ILLEGAL!").build();
        }

        //总条数
        int total = list.size();
        int temporary = total/size;
        int remainder = total%size;
        //总页数
        int pageTotal = remainder == 0 ? temporary : temporary+1;

        List<Po> baseData = new ArrayList<>();
        for(int i = (page-1)*size ; i< page*size ; i++ ){
            if( i < total){
                baseData.add(list.get(i));
            }
        }

        return  PageResponse.<Po>builder()
                .data(baseData)
                .page(page)
                .size(size)
                .pageTotal(pageTotal)
                .total(total).build();
    }

}
