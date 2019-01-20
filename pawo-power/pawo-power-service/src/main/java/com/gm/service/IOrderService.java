package com.gm.service;

import com.baomidou.mybatisplus.service.IService;
import com.gm.user.ShoppingOrderVo;
import com.gm.user.ShoppingOrders;
import com.gm.user.ShoppingOrdersRequest;

/**
 * 普通订单处理接口
 */
public interface IOrderService extends IService<ShoppingOrders> {


    /**
     * 下单接口
     *
     * @return 结果
     */
    ShoppingOrderVo submit(ShoppingOrdersRequest orderRequest);
}
