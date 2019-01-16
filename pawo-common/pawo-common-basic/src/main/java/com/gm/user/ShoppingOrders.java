/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-common All rights reserved.
 */

package com.gm.user;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

import lombok.Data;

/**
 * <p> </p>
 *
 * <pre> Created: 2019-01-16 22:47  </pre>
 * <pre> Project: pawo-common  </pre>
 *
 * @author gmzhao
 * @version 1.0
 * @since JDK 1.7
 */
/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. 赵贵明
 * pawo-server All rights reserved.
 */

@TableName("SHOPPING_ORDERS")
@Data
public class ShoppingOrders extends Model<ShoppingOrders> {

    private static final long serialVersionUID = 6463201706516176118L;

    /**
     * 物理主键
     */
    @TableId
    private Long   iid;
    /**
     * 订单编号
     */
    private String sn;
    /**
     * 关联父订单SN
     */
    private String  parentSn;
    /**
     * 订单商品类型代码
     */
    private String  orderTypeCode;
    /**
     * 下单类型（秒杀/下单）
     */
    private Integer transactionType;
    /**
     * 商品编号
     */
    private String  goodsCode;
    /**
     * 价格
     */
    private Double  price;
    /**
     * 下单数量
     */
    private Integer volume;
    /**
     * 剩余数量
     */
    private Integer volumeTotal;
    /**
     * 已交易数量
     */
    private Integer volumeTraded;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 订单状态信息
     */
    private String  statusMsg;
    /**
     * 下单时间
     */
    private String  insertTime;
    /**
     * 用户编号
     */
    private Integer userId;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 0-正常;1-删除
     */
    @TableLogic
    private Integer deleteFlag;


    @Override
    protected Serializable pkVal() {
        return this.iid;
    }



}

