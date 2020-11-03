package com.smart.transaction.entity;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @Author Emilia
 * @Since 2020.11.03 17:57
 */
@Data
public class Product {
    private Integer productId;
    private String name;
    private BigDecimal price;
    private String img;
}
