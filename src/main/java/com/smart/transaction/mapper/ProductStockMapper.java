package com.smart.transaction.mapper;

import com.smart.transaction.entity.ProductStock;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Emilia
 * @Since 2020.11.03 19:22
 */
public interface ProductStockMapper {
    /**
     * 查询商品库存信息
     * @param ids
     * @return
     */
    List<ProductStock> selectProductSize(@Param("ids")List<Integer> ids);
}
