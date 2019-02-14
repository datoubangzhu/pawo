package com.gm.service;

import com.baomidou.mybatisplus.service.IService;
import com.gm.goods.GoodsOrders;
import com.gm.order.ShoppingOrderVo;
import com.gm.order.ShoppingOrders;
import com.gm.order.ShoppingOrdersRequest;

import java.util.List;


/**
 * 普通订单处理接口
 *
 * @author gmzhao
 */
public interface IOrderService extends IService<ShoppingOrders> {

    /**
     * 数据库下单接口
     *
     * @param orderRequest request param
     * @return 结果
     */
    ShoppingOrderVo submit(ShoppingOrdersRequest orderRequest);
    /**
     * (测试)缓存数据库下单接口
     *
     * @param orderRequest request param
     * @return 结果
     */
    ShoppingOrderVo submitCache(ShoppingOrdersRequest orderRequest);
    /**
     * MQ下单接口
     *
     * @param orderRequest request param
     * @return 结果
     */
    ShoppingOrderVo submitQueue(ShoppingOrdersRequest orderRequest);
    /**
     * 获取商品信息
     *
     * @return 商品信息
     */
    List<GoodsOrders> list();
}
