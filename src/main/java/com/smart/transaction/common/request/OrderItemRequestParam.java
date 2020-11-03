package com.smart.transaction.common.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Emilia
 * @Since 2020.11.03 17:51
 */
@Data
public class OrderItemRequestParam {
    private Integer productId;
    private String name;
    private BigDecimal price;
    private String img;
    /**
     * 购买的数据
     */
    private Integer quantity;

    public OrderItemRequestParam(Integer productId, String name, BigDecimal price, String img, Integer quantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.img = img;
        this.quantity = quantity;
    }
}
