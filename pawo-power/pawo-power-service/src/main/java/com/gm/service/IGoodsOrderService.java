package com.gm.service;

import com.baomidou.mybatisplus.service.IService;
import com.gm.goods.GoodsOrders;


/**
 * goods service handle
 *
 * @author gmzhao
 */
public interface IGoodsOrderService extends IService<GoodsOrders> {

    /**
     * 根据卖家编号和商品编号获取加锁后的商品单
     *
     * @param sellerSn 卖家编号
     * @param goodsCode 商品编号
     * @return 商品单
     */
    GoodsOrders getForUpdate(String sellerSn, String goodsCode);
}
