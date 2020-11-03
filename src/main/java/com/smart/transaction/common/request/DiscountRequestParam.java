package com.smart.transaction.common.request;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Emilia
 * @Since 2020.11.03 19:39
 */
@Data
public class DiscountRequestParam {

    private Integer discountId;
    /**
     * 优惠券金额
     */
    private BigDecimal money;
}
