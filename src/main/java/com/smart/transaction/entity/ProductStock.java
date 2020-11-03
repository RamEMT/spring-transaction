package com.smart.transaction.entity;

import lombok.Data;

/**
 * @author Emilia
 */
@Data
public class ProductStock {
    /**
     *
     */
    private Integer stockId;

    /**
     * 总库存数
     */
    private Integer total;

    /**
     *
     */
    private Integer stock1;

    /**
     *
     */
    private Integer productId;
}

