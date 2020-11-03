package com.smart.transaction.serervice.impl;

import com.smart.transaction.common.request.OrderItemRequestParam;
import com.smart.transaction.common.request.OrderRequestParams;
import com.smart.transaction.entity.Order;
import com.smart.transaction.entity.OrderItem;
import com.smart.transaction.mapper.OrderItemMapper;
import com.smart.transaction.mapper.OrderMapper;
import com.smart.transaction.mapper.ProductStockMapper;
import com.smart.transaction.serervice.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.List;

/**
 * @Author Emilia
 * @Since 2020.11.03 15:11
 * <p>
 * 1.创建订单
 * 2.取消订单
 * 3.修改订单
 * 4.查看订单
 * 5.查看订单详情
 * 6.删除订单
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
    @Resource
    OrderMapper orderMapper;
    @Resource
    OrderItemMapper orderItemMapper;
    @Resource
    ProductStockMapper productStockMapper;

    @Override
    public String createOrder(OrderRequestParams orderRequest) {
        Order order = new Order();
        //设置订单号 -- 雪花算法，美团leaf
        order.setOrderSn("123456");
        //设置基本信息
        order.setReceiverName(orderRequest.getUsername());
        order.setReceiverPhone(orderRequest.getPhone());
        order.setReceiverDetailAddress(orderRequest.getAddress());

        //计算总金额
        List<OrderItemRequestParam> orderItemList = orderRequest.getOrderItemList();
        BigDecimal totalPrice = null;
        orderItemList.forEach(orderItem -> {
            //获取商品库存
            int productSize = productStockMapper.selectProductSize(orderItem.getProductId());
            //判断商品库存是否大于购买数量
            if (productSize >= orderItem.getQuantity()) {
                //商品单价
                BigDecimal price = orderItem.getPrice();
                //商品数量
                Integer quantity = orderItem.getQuantity();
                //计算总价格
                totalPrice.add(price.multiply(new BigDecimal(quantity)));
            } else {
                throw new RuntimeException("商品库存不足！");
            }
        });
        //计算商品总价减去优惠券和红包之后的价格
        BigDecimal subtract = totalPrice.subtract(orderRequest.getDiscount().getMoney());
        order.setTotalAmount(subtract);
        //
        orderMapper.insertOrder(order);
        //保存订单详情
        orderItemList.forEach(orderItemRequest -> {
            OrderItem orderItem = new OrderItem();
            BeanUtils.copyProperties(orderItemRequest,orderItem);
            //设置订单号
            orderItem.setOrderNo("123");
            orderItem.setOrderId(order.getOrderId());
            orderItemMapper.insert(orderItem);
        });

        return null;
    }
}
