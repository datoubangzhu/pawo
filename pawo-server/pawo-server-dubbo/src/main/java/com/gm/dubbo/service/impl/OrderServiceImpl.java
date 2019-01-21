/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-server All rights reserved.
 */

package com.gm.dubbo.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gm.config.exception.PawoError;
import com.gm.config.exception.PawoException;
import com.gm.goods.GoodsOrders;
import com.gm.mapper.OrderMapper;
import com.gm.order.ShoppingOrderVo;
import com.gm.order.ShoppingOrders;
import com.gm.order.ShoppingOrdersRequest;
import com.gm.service.IDubboOrderService;
import com.gm.util.OrderUtil;
import com.sun.corba.se.impl.oa.toa.TOA;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

import lombok.extern.slf4j.Slf4j;

/**
 * <p> 订单处理rpc 实现类</p>
 *
 * <pre> Created: 2019-01-16 22:42  </pre>
 * <pre> Project: pawo-server  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Service
@Component
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper,ShoppingOrders> implements IDubboOrderService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    /**
     * redis超时时间 毫秒
     */
    private static final int EXPIRE_TIME = 2000;


    @Override
    public ShoppingOrderVo fastSubmit(ShoppingOrdersRequest ordersRequest) {
        final String sn = OrderUtil.getOrderSn();
        ordersRequest.setSellerSn(sn);
        log.info("收到订单信息---，单号：{},订单详情：{}",sn,ordersRequest.toString());

        RedisConnectionFactory redisConnectionFactory = redisTemplate.getConnectionFactory();
        RedisConnection connection = redisConnectionFactory.getConnection();
        final byte[] key = sn.getBytes();
        final long expireValue = EXPIRE_TIME/1000;
        final byte[] value = ordersRequest.toString().getBytes();
        final long endTime = System.currentTimeMillis()+EXPIRE_TIME;

        while (System.currentTimeMillis()<endTime) {
            boolean setnx = connection.setNX(key,value);
            if(setnx){
                connection.expire(key,expireValue);
                return handleCache(ordersRequest);
            }
            //如果没有获取到锁，重试到超时时间
        }
        throw new PawoException("宝贝已经没有了！", PawoError.SUBMIT_FAILURE.getCode());
    }


    /**
     * 处理rpc订单
     *
     * @param shoppingRequest 订单请求
     * @return 下单结果
     */
    private ShoppingOrderVo handleCache(ShoppingOrdersRequest shoppingRequest){
        final String sellerSn = shoppingRequest.getSellerSn();
        final String goodsCode = shoppingRequest.getGoodsCode();
        final String marketSn = sellerSn+goodsCode;

        GoodsOrders goodsOrders = (GoodsOrders)redisTemplate.opsForValue().get(marketSn);
        final int traded = goodsOrders.getVolumeTraded();
        final int total = goodsOrders.getVolumeTotal();
        final int rest = total-traded;
        //1.余量控制
        final int newTraded = traded + shoppingRequest.getVolume();
        if(newTraded> total){
            throw new PawoException("宝贝存货只有"+rest+"了！",PawoError.SUBMIT_FAILURE.getCode());
        }
        goodsOrders.setVolumeTraded(newTraded);
        redisTemplate.delete(marketSn);
        redisTemplate.opsForValue().set(marketSn,goodsCode);
        //2.返回信息设置
        ShoppingOrderVo shoppingOrderVo = new ShoppingOrderVo();
        shoppingOrderVo.setSn(shoppingRequest.getSn());
        return shoppingOrderVo;
    }

}























