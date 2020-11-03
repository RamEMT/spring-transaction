package com.smart.transaction.mapper;

import org.apache.ibatis.annotations.Param;

/**
 * @Author Emilia
 * @Since 2020.11.03 19:22
 */
public interface ProductStockMapper {
    /**
     * 查询商品库存信息
     * @param productId
     * @return
     */
    int selectProductSize(@Param("productId")int productId);
}
