package com.gm.user;

import lombok.Data;

@Data
public class ShoppingOrderVo {
    /**
     * 订单编号
     */
    private String sn;
    /**
     * 下单类型（秒杀/下单）
     */
    private Integer transactionType;
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
     * 订单状态信息
     */
    private String  statusMsg;
    /**
     * 下单时间
     */
    private String  insertTime;
}
