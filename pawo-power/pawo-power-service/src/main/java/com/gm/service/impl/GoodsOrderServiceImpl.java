package com.gm.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gm.goods.GoodsOrders;
import com.gm.mapper.GoodOrderMapper;
import com.gm.service.IGoodsOrderService;

import org.springframework.stereotype.Service;

/**
 * 商品订单service
 *
 * @author gmzhao
 */
@Service
public class GoodsOrderServiceImpl extends ServiceImpl<GoodOrderMapper,GoodsOrders> implements IGoodsOrderService  {
    @Override
    public GoodsOrders getForUpdate(String sellerSn, String goodsCode) {
        return baseMapper.selectForUpdate(sellerSn,goodsCode);
    }
}
