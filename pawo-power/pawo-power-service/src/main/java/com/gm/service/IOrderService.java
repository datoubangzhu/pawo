package com.gm.service;

import com.baomidou.mybatisplus.service.IService;
import com.gm.order.ShoppingOrderVo;
import com.gm.order.ShoppingOrders;
import com.gm.order.ShoppingOrdersRequest;


/**
 * 普通订单处理接口
 *
 * @author gmzhao
 */
public interface IOrderService extends IService<ShoppingOrders> {


    /**
     * 下单接口
     *
     * @param orderRequest request param
     * @return 结果
     */
    ShoppingOrderVo submit(ShoppingOrdersRequest orderRequest);
}
