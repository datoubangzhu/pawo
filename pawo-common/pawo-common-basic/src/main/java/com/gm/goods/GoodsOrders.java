/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 * Copyright (c) 2012-2019. haiyi Inc.
 * pawo-common All rights reserved.
 */

package com.gm.goods;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableLogic;
import com.baomidou.mybatisplus.annotations.TableName;

import java.io.Serializable;

@TableName("GOODS_ORDER")
public class GoodsOrders extends Model<GoodsOrders> {
    @TableId
    private Long iid;
    /**
     * 卖家编号
     */
    private String sellerSn;
    /**
     * 商品编号
     */
    private String goodsCode;
    /**
     * 商品名称
     */
    private String goodsName;
    /**
     * 商品类型
     */
    private String goodsType;
    /**
     * 定价
     */
    private String price;
    /**
     * 成本价
     */
    private String lowestPrice;
    /**
     * 剩余数量
     */
    private Integer volumeTotal;
    /**
     * 已交易数量
     */
    private Integer volumeTraded;
    /**
     * 创建人
     */
    private String creater;
    /**
     * 创建时间
     */
    private Long createTime;
    /**
     * 状态：01-正常，02-下架
     */
    private String goodsStatus;

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

    public String getSellerSn() {
        return sellerSn;
    }

    public void setSellerSn(String sellerSn) {
        this.sellerSn = sellerSn;
    }

    public String getGoodsCode() {
        return goodsCode;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public void setGoodsCode(String goodsCode) {
        this.goodsCode = goodsCode;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLowestPrice() {
        return lowestPrice;
    }

    public void setLowestPrice(String lowestPrice) {
        this.lowestPrice = lowestPrice;
    }

    public Integer getVolumeTotal() {
        return volumeTotal;
    }

    public void setVolumeTotal(Integer volumeTotal) {
        this.volumeTotal = volumeTotal;
    }

    public Integer getVolumeTraded() {
        return volumeTraded;
    }

    public void setVolumeTraded(Integer volumeTraded) {
        this.volumeTraded = volumeTraded;
    }

    public String getCreater() {
        return creater;
    }

    public void setCreater(String creater) {
        this.creater = creater;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public String getGoodsStatus() {
        return goodsStatus;
    }

    public void setGoodsStatus(String goodsStatus) {
        this.goodsStatus = goodsStatus;
    }

    public Integer getDeleteFlag() {
        return deleteFlag;
    }

    public void setDeleteFlag(Integer deleteFlag) {
        this.deleteFlag = deleteFlag;
    }

    @Override
    public String toString() {
        return "GoodsOrders{" +
                "iid=" + iid +
                ", sellerSn='" + sellerSn + '\'' +
                ", goodsCode='" + goodsCode + '\'' +
                ", goodsName='" + goodsName + '\'' +
                ", goodsType='" + goodsType + '\'' +
                ", price='" + price + '\'' +
                ", lowestPrice='" + lowestPrice + '\'' +
                ", volumeTotal=" + volumeTotal +
                ", volumeTraded=" + volumeTraded +
                ", creater='" + creater + '\'' +
                ", createTime=" + createTime +
                ", goodsStatus='" + goodsStatus + '\'' +
                ", deleteFlag=" + deleteFlag +
                '}';
    }
}
