package com.smart.transaction.common.request;

import lombok.Data;

import java.util.List;

/**
 * @Author Emilia
 * @Since 2020.11.03 17:48
 */
@Data
public class OrderRequestParams {
    private Integer userId;
    /**
     * 收件人姓名
     */
    private String username;
    private String phone;
    private String address;
    /**
     * 购买的商品信息
     */
    private List<OrderItemRequestParam> orderItemList;
    /**
     * 优惠券
     */
    private DiscountRequestParam discount;
}
