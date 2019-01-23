/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. gmzhao Inc.
 * pawo-common All rights reserved.
 */

package com.gm.order;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

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


@TableName("SHOPPING_ORDERS")
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
     * 下单类型（秒杀/下单）
     */
    private String transactionType;
    /**
     * 卖家编号
     */
    private String  sellerSn;
    /**
     * 商品编号
     */
    private String  goodsCode;
    /**
     * 下单价格
     */
    private Double  price;
    /**
     * 下单数量
     */
    private Integer volume;
    /**
     * 订单状态
     */
    private String orderStatus;
    /**
     * 订单备注信息
     */
    private String  remarkMsg;
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

    public Long getIid() {
        return iid;
    }

    public void setIid(Long iid) {
        this.iid = iid;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getRemarkMsg() {
        return remarkMsg;
    }

    public void setRemarkMsg(String remarkMsg) {
        this.remarkMsg = remarkMsg;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Integer getVolume() {
        return volume;
    }

    public void setVolume(Integer volume) {
        this.volume = volume;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getSellerSn() {
        return sellerSn;
    }

    public void setSellerSn(String sellerSn) {
        this.sellerSn = sellerSn;
    }

    public String getInsertTime() {
        return insertTime;
    }

    public void setInsertTime(String insertTime) {
        this.insertTime = insertTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "ShoppingOrders{" +
                "iid=" + iid +
                ", sn='" + sn + '\'' +
                ", transactionType=" + transactionType +
                ", goodsCode='" + goodsCode + '\'' +
                ", price=" + price +
                ", volume=" + volume +
                ", orderStatus='" + orderStatus + '\'' +
                ", remarkMsg='" + remarkMsg + '\'' +
                ", insertTime='" + insertTime + '\'' +
                ", sellerSn='" + sellerSn + '\'' +
                ", userId=" + userId +
                ", userName='" + userName + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}

