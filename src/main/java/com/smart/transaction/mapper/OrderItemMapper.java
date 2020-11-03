package com.smart.transaction.mapper;

import com.smart.transaction.entity.OrderItem;
import org.apache.ibatis.annotations.Param;

/**
 * @Author Emilia
 * @Since 2020.11.03 18:36
 */
public interface OrderItemMapper {
    /**
     * 保存订单详情
     * @param orderItem
     */
    void insert(@Param("orderItem") OrderItem orderItem);
}
