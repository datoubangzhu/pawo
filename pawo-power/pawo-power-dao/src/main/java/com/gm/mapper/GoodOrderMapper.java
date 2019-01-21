package com.gm.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.gm.goods.GoodsOrders;

import org.apache.ibatis.annotations.Param;

/**
 * 商品dao  mapper
 *
 * @author: gmzhao
 * time: 2019/1/12/10:08
 */
public interface GoodOrderMapper extends BaseMapper<GoodsOrders> {

    /**
     * 根据卖家编号和商品编号查找商品单并对其加锁
     *
     * @param sellerSn 卖家编号
     * @param goodsCode 商品编码
     * @return 订单信息
     */
    GoodsOrders selectForUpdate(@Param("sellerSn")String sellerSn,
                               @Param("goodsCode")String goodsCode);
}
