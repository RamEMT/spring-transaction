package com.smart.transaction.mapper;

import com.smart.transaction.entity.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author Emilia
 * @Since 2020.11.04 19:29
 */
public interface ProductMapper {
    /**
     * 根据商品id查询商品价格
     * @param ids
     * @return
     */
    List<Product> selectListById(@Param("ids") List<Integer> ids);
}
