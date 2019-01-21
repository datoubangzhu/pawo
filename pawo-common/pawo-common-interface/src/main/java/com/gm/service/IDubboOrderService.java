/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-common All rights reserved.
 */

package com.gm.service;


import com.baomidou.mybatisplus.service.IService;
import com.gm.order.ShoppingOrders;
import com.gm.order.ShoppingOrdersRequest;

/**
 * <p> 订单处理服务接口</p>
 *
 * <pre> Created: 2019-01-16 22:46  </pre>
 * <pre> Project: pawo-common  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public interface IDubboOrderService extends IService<ShoppingOrders> {


    /**
     *  抢单服务
     *
     * @param ordersRequest 订单请求信息
     * @return 是否抢单成功
     */
    boolean fastSubmit(ShoppingOrdersRequest ordersRequest);

}
