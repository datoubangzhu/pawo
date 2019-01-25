/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-power All rights reserved.
 */

package com.gm.dubbo.service;

import com.gm.order.ShoppingOrderVo;
import com.gm.order.ShoppingOrdersRequest;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-25 19:43  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
public interface IRpcOrderService {

    /**
     * rpc 下单接口
     *
     * @param ordersRequest 订单请求信息
     * @return 下单结果信息
     */
    ShoppingOrderVo fastSubmit(ShoppingOrdersRequest ordersRequest);
}
