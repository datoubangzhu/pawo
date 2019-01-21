package com.gm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.constant.error.PawoError;
import com.gm.exception.PawoException;
import com.gm.goods.GoodsOrders;
import com.gm.goods.GoodsStatus;
import com.gm.mapper.OrderMapper;
import com.gm.order.ShoppingOrderVo;
import com.gm.order.ShoppingOrders;
import com.gm.order.ShoppingOrdersRequest;
import com.gm.service.IGoodsOrderService;
import com.gm.service.IOrderService;
import com.gm.user.*;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * shoppingorder service interface
 *
 * @author gmzhao
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper,ShoppingOrders> implements IOrderService {

    private final MapperFacade mapperFacade;
    private final IGoodsOrderService goodsOrderService;

    @Autowired
    public OrderServiceImpl(
//            MapperFacade mapperFacade,
                            IGoodsOrderService goodsOrderService) {
//        this.mapperFacade = mapperFacade;
        this.mapperFacade = null;
        this.goodsOrderService = goodsOrderService;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShoppingOrderVo submit(ShoppingOrdersRequest shoppingOrder) {
        final String sn = shoppingOrder.getSellerSn();
        final String code = shoppingOrder.getGoodsCode();
        //1.加锁
        GoodsOrders goodsOrders = goodsOrderService.getForUpdate(sn,code);
        //2.商品数额更改,此处采取悲观锁，支持nginx负载均衡
        final int volumeTraded = goodsOrders.getVolumeTraded();
        final Integer volume = shoppingOrder.getVolume();
        if(volume<=0){
            throw new PawoException("宝贝已经没有了！",PawoError.AUTH_FAILURE.getCode());
        }
        final int newTraded = volume + volumeTraded;
        goodsOrders.setVolumeTraded(newTraded);
        boolean ok = goodsOrderService.update(goodsOrders, new EntityWrapper<GoodsOrders>()
                .eq("SELLER_SN",sn)
                .eq("GOODS_CODE",code)
                .eq("STATUS", GoodsStatus.NORMAL)
                .eq("DELETE_FLAG",0));
        if(!ok){
            throw new PawoException("下单失败！",PawoError.SUBMIT_FAILURE.getCode());
        }
        //3.订单入库
        ShoppingOrders shoppingEntity = mapperFacade.map(shoppingOrder,ShoppingOrders.class);
        //TODO 3.1生成订单编号
        shoppingEntity.setSn(String.valueOf(18080808));
        baseMapper.insert(shoppingEntity);
        //4.返回页面结果
        return mapperFacade.map(shoppingEntity,ShoppingOrderVo.class);
    }
}
