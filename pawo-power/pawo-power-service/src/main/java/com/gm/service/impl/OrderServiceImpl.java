package com.gm.service.impl;

import cn.hutool.core.util.ObjectUtil;
import com.google.common.collect.Lists;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.gm.config.exception.PawoError;
import com.gm.config.exception.PawoException;
import com.gm.config.rabbitmq.PawoMqConstant;
import com.gm.goods.GoodsOrders;
import com.gm.goods.GoodsStatus;
import com.gm.mapper.OrderMapper;
import com.gm.order.ShoppingOrderVo;
import com.gm.order.ShoppingOrders;
import com.gm.order.ShoppingOrdersRequest;
import com.gm.service.IGoodsOrderService;
import com.gm.service.IOrderService;
import com.gm.util.OrderUtil;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import lombok.extern.slf4j.Slf4j;
import ma.glasnost.orika.MapperFacade;

import static com.gm.config.rabbitmq.PawoMqConstant.ORDER_EXCHANGE;
import static com.gm.config.rabbitmq.PawoMqConstant.ORDER_KEY;

/**
 * shoppingorder service interface
 *
 * @author gmzhao
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, ShoppingOrders> implements IOrderService {

    private final MapperFacade                  mapperFacade;
    private final IGoodsOrderService            goodsOrderService;
    private final RedisTemplate<String, Object> redisTemplate;
    private final RabbitTemplate                template;

    /**
     * redis超时时间 毫秒
     */
    private static final int    EXPIRE_TIME         = 2000;
    /**
     * redis商品数据库键值
     */
    private static final String PAWO_GOODS          = "pawo:goods";
    /**
     * redis订单数据库键值
     */
    private static final String PAWO_SHOPPING_ORDER = "pawo:order:";


    @Autowired
    public OrderServiceImpl(MapperFacade mapperFacade,
                            IGoodsOrderService goodsOrderService,
                            RedisTemplate<String, Object> redisTemplate,
                            RabbitTemplate template) {
        this.mapperFacade = mapperFacade;
        this.goodsOrderService = goodsOrderService;
        this.redisTemplate = redisTemplate;
        this.template = template;
    }


    @Override
    @Transactional(rollbackFor = Exception.class)
    public ShoppingOrderVo submit(ShoppingOrdersRequest shoppingOrder) {
        final String sn = shoppingOrder.getSellerSn();
        final String code = shoppingOrder.getGoodsCode();
        //1.加锁
        GoodsOrders goodsOrders = goodsOrderService.getForUpdate(sn, code);
        //2.商品数额更改,此处采取悲观锁，支持nginx负载均衡
        final int volumeTraded = goodsOrders.getVolumeTraded();
        final Integer volume = shoppingOrder.getVolume();
        if (volume <= 0) {
            throw new PawoException("宝贝已经没有了！", PawoError.AUTH_FAILURE.getCode());
        }
        final int newTraded = volume + volumeTraded;
        goodsOrders.setVolumeTraded(newTraded);
        boolean ok = goodsOrderService.update(goodsOrders, new EntityWrapper<GoodsOrders>()
                .eq("SELLER_SN", sn)
                .eq("GOODS_CODE", code)
                .eq("STATUS", GoodsStatus.NORMAL)
                .eq("DELETE_FLAG", 0));
        if (!ok) {
            throw new PawoException("下单失败！", PawoError.SUBMIT_FAILURE.getCode());
        }
        //3.订单入库
        ShoppingOrders shoppingEntity = mapperFacade.map(shoppingOrder, ShoppingOrders.class);
        final String orderSn = OrderUtil.getOrderSn();
        shoppingEntity.setSn(orderSn);
        baseMapper.insert(shoppingEntity);
        //4.返回页面结果
        return mapperFacade.map(shoppingEntity, ShoppingOrderVo.class);
    }


    @Override
    public ShoppingOrderVo submitCache(ShoppingOrdersRequest ordersRequest) {
        final String sn = OrderUtil.getOrderSn();
        ordersRequest.setSn(sn);
        log.info("准备通过更新缓存处理订单信息================================，单号：{},订单详情：{}", sn, ordersRequest.toString());

        RedisConnectionFactory redisConnectionFactory = redisTemplate.getConnectionFactory();
        RedisConnection connection = redisConnectionFactory.getConnection();
        final byte[] key = sn.getBytes();
        final long expireValue = EXPIRE_TIME / 1000;
        final byte[] value = ordersRequest.toString().getBytes();
        final long endTime = System.currentTimeMillis() + EXPIRE_TIME;

        while (System.currentTimeMillis() < endTime) {
            boolean setnx = connection.setNX(key, value);
            if (setnx) {
                connection.expire(key, expireValue);
                return handleCache(ordersRequest, connection, key);
            }
            //如果没有获取到锁，重试到超时时间
        }
        throw new PawoException("宝贝已经没有了！", PawoError.SUBMIT_FAILURE.getCode());
    }

    @Override
    public ShoppingOrderVo submitQueue(ShoppingOrdersRequest ordersRequest) {
        final String sn = OrderUtil.getOrderSn();
        ordersRequest.setSn(sn);
        RedisConnectionFactory redisConnectionFactory = redisTemplate.getConnectionFactory();
        RedisConnection connection = redisConnectionFactory.getConnection();
        //将商品号作为唯一键
        final byte[] key = ordersRequest.getGoodsCode().getBytes();
        final long expireValue = EXPIRE_TIME / 1000;
        final byte[] value = ordersRequest.toString().getBytes();
        final long endTime = System.currentTimeMillis() + EXPIRE_TIME;

        while (System.currentTimeMillis() < endTime) {
            log.info("开始尝试加锁，当前时间"+System.currentTimeMillis()+"   结束时间"+endTime);
            boolean setnx = connection.setNX(key, value);
            if (setnx) {
                log.info("成功获取锁==================="+"   当前时间"+System.currentTimeMillis());
                connection.expire(key, expireValue);
                return handleQueue(ordersRequest, connection, key);
            }
            //如果没有获取到锁，重试到超时时间
        }
        throw new PawoException("宝贝已经没有了！", PawoError.SUBMIT_FAILURE.getCode());
    }

    @Override
    public List<GoodsOrders> list() {
        List<Object> goodsCodes = redisTemplate.boundHashOps(PAWO_GOODS).values();
        List<GoodsOrders> list = Lists.newArrayListWithExpectedSize(goodsCodes.size());
        for (Object obj : goodsCodes) {
            list.add((GoodsOrders) obj);
        }
        return list;
    }

    /**
     * 通过缓存处理订单
     *
     * @param shoppingRequest 订单请求
     * @return 下单结果
     */
    private ShoppingOrderVo handleCache(ShoppingOrdersRequest shoppingRequest, RedisConnection connection, byte[] key) {
        final String goodsCode = shoppingRequest.getGoodsCode();
        GoodsOrders goodsOrders = (GoodsOrders) redisTemplate.opsForHash().get(PAWO_GOODS, goodsCode);
        if(ObjectUtil.isNull(goodsOrders)){
            throw new PawoException("没有可用缓存信息",PawoError.SUBMIT_FAILURE.getCode());
        }
        final int traded = goodsOrders.getVolumeTraded();
        final int total = goodsOrders.getVolumeTotal();
        final int rest = total - traded;
        //1.余量控制
        final int newTraded = traded + shoppingRequest.getVolume();
        if (newTraded > total) {
            throw new PawoException("宝贝存货只有" + rest + "了！", PawoError.SUBMIT_FAILURE.getCode());
        }
        goodsOrders.setVolumeTraded(newTraded);
        //1.1更新商品数量信息
        redisTemplate.opsForHash().delete(PAWO_GOODS, goodsCode);
        redisTemplate.opsForHash().put(PAWO_GOODS, goodsCode, goodsOrders);
        //1.2删除锁定key值
        connection.del(key);
        connection.close();
        //2.1 订单信息入库
        ShoppingOrders shoppingOrders = mapperFacade.map(shoppingRequest, ShoppingOrders.class);
        redisTemplate.opsForHash().put(PAWO_SHOPPING_ORDER, shoppingRequest.getSn(), shoppingOrders);
        //2.2返回信息设置
        return mapperFacade.map(shoppingRequest, ShoppingOrderVo.class);
    }


    /**
     * MQ 处理订单
     *
     * @param shoppingRequest 订单请求
     * @return 下单结果
     */
    private ShoppingOrderVo handleQueue(ShoppingOrdersRequest shoppingRequest, RedisConnection connection, byte[] key) {
        log.info("进入handQueue，准备进行处理");
        final String goodsCode = shoppingRequest.getGoodsCode();
        log.info("handQueue=======获取商品库存信息");
        GoodsOrders goodsOrders = (GoodsOrders) redisTemplate.opsForHash().get(PAWO_GOODS, goodsCode);
        if(ObjectUtil.isNull(goodsOrders)){
            throw new PawoException("没有可用缓存信息",PawoError.SUBMIT_FAILURE.getCode());
        }
        log.info("handQueue=======获取商品库存信息成功");
        final int traded = goodsOrders.getVolumeTraded();
        final int total = goodsOrders.getVolumeTotal();
        final int rest = total - traded;
        //1.余量控制
        final int newTraded = traded + shoppingRequest.getVolume();
        if (newTraded > total) {
            throw new PawoException("宝贝存货只有" + rest + "了！", PawoError.SUBMIT_FAILURE.getCode());
        }
        goodsOrders.setVolumeTraded(newTraded);
        //1.1更新商品数量信息
        redisTemplate.opsForHash().delete(PAWO_GOODS, goodsCode);
        redisTemplate.opsForHash().put(PAWO_GOODS, goodsCode, goodsOrders);
        //1.2删除锁定key值
        connection.del(key);
        //1.3切记切记要记得关闭连接池！！！！！！，踩了个坑
        connection.close();
        //2.发送到mq
        log.info("发送前===============》");
        template.convertAndSend(ORDER_EXCHANGE,ORDER_KEY,shoppingRequest);
        log.info("订单发送 =============》 " + shoppingRequest.toString());
        return mapperFacade.map(shoppingRequest, ShoppingOrderVo.class);
    }
}



