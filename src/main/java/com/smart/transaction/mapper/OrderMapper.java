package com.smart.transaction.mapper;

import com.smart.transaction.entity.Order;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Emilia
 * @Since 2020.11.03 18:36
 */
public interface OrderMapper {
    /**
     * 保存订单
     * @param order
     */
    void insertOrder(@Param("order") Order order);
}
