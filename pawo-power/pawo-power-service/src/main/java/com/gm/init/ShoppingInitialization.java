/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明 Inc.
 * pawo-power All rights reserved.
 */

package com.gm.init;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import com.gm.config.exception.PawoError;
import com.gm.config.exception.PawoException;
import com.gm.goods.GoodsOrders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import cn.hutool.core.collection.CollectionUtil;
import lombok.extern.slf4j.Slf4j;

/**
 * <p> 初始化数据 </p>
 *
 * <pre> Created: 2019-01-23 13:40  </pre>
 * <pre> Project: pawo-power  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
@EnableScheduling
@Component
@Slf4j
public class ShoppingInitialization {

    private final RedisTemplate<String, Object> redisTemplate;

    private static final String PAWO_GOODS = "pawo:goods";

    @Autowired
    public ShoppingInitialization(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }


    /**
     * 每天早上四点，开始重新初始化数据
     */
    @Scheduled(cron = "0 0 4 * * ? ")
    public void initGoods() {
        HashOperations<String, String,Object> hashOperations = redisTemplate.opsForHash();
        List redisList = hashOperations.values(PAWO_GOODS);
        if(CollectionUtil.isNotEmpty(redisList)){
            return;
        }
        try {
            String path = "pawo_goods_init.txt";
            FileReader fileReader = new FileReader(path);
            BufferedReader reader = new BufferedReader(fileReader);
            String readLine;
            List<Map<String, String>> list = Lists.newArrayList();
            Map<String, String> paramMap = Maps.newHashMap();
            //以 } 作为结尾，如果遇到则说明完成一个周期，这时候将map放入list，并重新生成map，开始下个循环
            while ((readLine = reader.readLine()) != null) {
                boolean end = readLine.contains("}");
                if(end){
                    list.add(paramMap);
                    paramMap = Maps.newHashMap();
                    continue;
                }
                boolean ok = readLine.contains(":");
                if (!ok) {
                    continue;
                }
                String[] param = readLine.split(":");
                String key = format(param[0]);
                String value = format(param[1]);
                paramMap.put(key,value);
            }
            fileReader.close();
            reader.close();
            log.info("========================》" + list.toString());
            this.handle(list);
        } catch (IOException exception) {
            log.error("初始化商品数据错误失败： {} ", exception);
            throw new PawoException("初始化商品数据错误", PawoError.INIT_GOODS_FAILURE.getCode());
        }
    }


    /**
     * 格式化字符串
     * 去除 \t , “ }
     *
     * @param param 参数
     * @return 格式化后的参数
     */
    private String format(String param) {
        String key1 = param.replace("\"", "");
        String key2 = key1.replace("\t", "");
        String key3 = key2.replace(",", "");
        return key3.replace("}","");
    }


    private void handle(List<Map<String, String>> list) {
        for (Map<String, String> map : list) {
            GoodsOrders goodsOrder = new GoodsOrders();
            goodsOrder.setSellerSn(map.get("sellerSn"));
            goodsOrder.setGoodsCode(map.get("goodsCode"));
            goodsOrder.setGoodsName(map.get("goodsName"));
            goodsOrder.setGoodsType(map.get("goodsType"));
            goodsOrder.setPrice(map.get("price"));
            goodsOrder.setLowestPrice(map.get("lowestPrice"));
            goodsOrder.setVolumeTotal(Integer.valueOf(map.get("volumeTotal")));
            goodsOrder.setVolumeTraded(Integer.valueOf(map.get("volumeTraded")));
            goodsOrder.setCreater(map.get("creater"));
            goodsOrder.setCreateTime(Long.valueOf(map.get("createTime")));
            goodsOrder.setGoodsStatus(map.get("goodsStatus"));
            goodsOrder.setDeleteFlag(Integer.valueOf(map.get("deleteFlag")));
            redisTemplate.opsForHash().put(PAWO_GOODS,map.get("goodsCode"),goodsOrder);
        }
    }

}
