package com.gm.request;

import com.constant.TransactionTypeEnum;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * 下单请求
 */
@Data
public class OrderRequest implements Serializable{

    private static final long serialVersionUID = -7456676121418079829L;
    /**
     * 商品编号
     */
    @NotBlank
    private String  goodsCode;
    /**
     * 下单数量
     */
    @NotNull
    private Integer volume;
    /**
     * 价格
     */
    @NotNull
    private Double  price;
    /**
     * 下单类型（秒杀/下单）
     */
    @NotNull
    private TransactionTypeEnum transactionType;
    /**
     * 用户姓名
     */
    @NotNull
    private String  userName;
}
