package com.smart.transaction.common.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Emilia
 * @Since 2020.11.04 20:04
 *
 *  订单号
 *  商品信息
 *  订单信息
 */
@Data
public class OrderVO {
    /**
     * 订单号
     */
    private String orderSn;
    /**
     * 商品标题
     */
    private String subject;
    /**
     * 总价格
     */
    private BigDecimal totalPrice;

}
