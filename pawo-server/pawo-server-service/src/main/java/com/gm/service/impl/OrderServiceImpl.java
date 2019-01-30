/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-server All rights reserved.
 */

package com.gm.service.impl;

import com.gm.config.exception.PawoError;
import com.gm.config.exception.PawoException;
import com.gm.goods.GoodsOrders;
import com.gm.order.ShoppingOrders;
import com.gm.order.ShoppingOrdersRequest;
import com.gm.service.IOrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;

/**
 * <p> 服务端订单处理service实现类 </p>
 *
 * <pre> Created: 2019-01-30 12:21  </pre>
 * <pre> Project: pawo-server  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@Service
public class OrderServiceImpl implements IOrderService {

    @Resource
    private RedisTemplate<String,Object> redisTemplate;

    private final MapperFacade mapperFacade;


    /**
     * redis商品数据库键值
     */
    private static final String PAWO_GOODS          = "pawo:goods";
    /**
     * redis订单数据库键值
     */
    private static final String PAWO_SHOPPING_ORDER = "pawo:order:";


    public OrderServiceImpl(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }


    @Override
    public void mqSubmit(ShoppingOrdersRequest shoppingRequest) {
            RedisConnectionFactory connectionFactory = redisTemplate.getConnectionFactory();
            RedisConnection connection = connectionFactory.getConnection();
            final String goodsCode = shoppingRequest.getGoodsCode();
            GoodsOrders goodsOrders = (GoodsOrders) redisTemplate.opsForHash().get(PAWO_GOODS, goodsCode);
            final int traded = goodsOrders.getVolumeTraded();
            //1.更新库存。入mq之前已经进行量的控制，此处只更新量
            final int newTraded = traded + shoppingRequest.getVolume();
            goodsOrders.setVolumeTraded(newTraded);
            //1.1更新商品数量信息
            redisTemplate.opsForHash().delete(PAWO_GOODS, goodsCode);
            redisTemplate.opsForHash().put(PAWO_GOODS, goodsCode, goodsOrders);
            //1.2新增订单信息
            ShoppingOrders shoppingOrders = mapperFacade.map(shoppingRequest, ShoppingOrders.class);
            redisTemplate.opsForHash().put(PAWO_SHOPPING_ORDER, shoppingRequest.getSn(), shoppingOrders);
            //1.3删除锁定key值
            final String sn = shoppingRequest.getSn();
            final byte[] key = sn.getBytes();
            connection.del(key);
    }
}
