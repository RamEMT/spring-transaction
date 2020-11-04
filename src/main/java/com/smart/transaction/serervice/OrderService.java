package com.smart.transaction.serervice;

import com.smart.transaction.common.RequestEntity;
import com.smart.transaction.common.request.OrderRequestParams;
import com.smart.transaction.common.vo.OrderVO;

/**
 * @Author Emilia
 * @Since 2020.11.03 14:45
 */
public interface OrderService {
    /**
     * 创建订单
     * @param orderRequestParams 请求参数对象
     * @return
     */
    RequestEntity<OrderVO> createOrder(OrderRequestParams orderRequestParams);
}
