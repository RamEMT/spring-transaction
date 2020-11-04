package com.smart.transaction.serervice.impl;

import com.smart.transaction.common.RequestEntity;
import com.smart.transaction.common.request.OrderItemRequestParam;
import com.smart.transaction.common.request.OrderRequestParams;
import com.smart.transaction.common.vo.OrderVO;
import com.smart.transaction.entity.Order;
import com.smart.transaction.entity.OrderItem;
import com.smart.transaction.entity.Product;
import com.smart.transaction.entity.ProductStock;
import com.smart.transaction.mapper.*;
import com.smart.transaction.serervice.OrderService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Emilia
 * @Since 2020.11.03 15:11
 *
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
    @Resource
    ProductMapper productMapper;
    @Resource
    CarsMapper carsMapper;

    /**
     * 订单  团购订单  秒杀订单
     *  1 订单表
     *  2 订单详情表
     *  3 库存表
     *  4 清理购物车
     *  5 商品价格
     *  6 查询商品的价格  --->  价格发生改动，提示前端让用户重新发起订单
     *  7 库存  --->  库存是否够
     *  8 总金额 --->
     *
     *  确认订单
     *  订单号显示
     *  总金额
     *  商品的信息展示
     *  选择支付方式
     *  生成订单支付连接
     *  点击支付
     *  支付系统
     *      1. 订单号，支付方式
     * @param orderRequest
     * @return
     */
    @Override
    public RequestEntity<OrderVO> createOrder(OrderRequestParams orderRequest) {
        OrderVO orderVO = new OrderVO();
        try {
            Order order = new Order();
            //前缀+时间戳+自增
            //生成订单号 -- 雪花算法，美团leaf 必须唯一 分库分表
            order.setOrderSn("123456");
            //设置基本信息
            order.setReceiverName(orderRequest.getUsername());
            order.setReceiverPhone(orderRequest.getPhone());
            order.setReceiverDetailAddress(orderRequest.getAddress());

            //查看商品价格是否一致
            List<OrderItemRequestParam> orderItemList = orderRequest.getOrderItemList();

            List<Integer> ids = new ArrayList<>();
            orderItemList.forEach(item->{
                ids.add(item.getProductId());
            });
            //查询商品价格
            List<Product> products = productMapper.selectListById(ids);
            //如果两个集合长度不一致，表示有商品下架
            if (products == null || products.size() != orderItemList.size()){
                throw new RuntimeException("部分商品已下架！请重新下单");
            }
            //判断价格是否发生了改变
            if (isPriceChange(products,orderItemList)) {
                throw new RuntimeException("商品价格不一致！请重新下单");
            }
            //判断商品库存是否大于购买数量
            if (isEnough(orderItemList)) {
                throw new RuntimeException("商品库存不足！请重新下单");
            }

            //计算总金额
            BigDecimal totalPrice = null;
            for (OrderItemRequestParam orderItem : orderItemList) {
                //商品单价
                BigDecimal price = orderItem.getPrice();
                //商品数量
                Integer quantity = orderItem.getQuantity();
                //计算总价格
                totalPrice = totalPrice.add(price.multiply(new BigDecimal(quantity)));
            }

            //计算商品总价减去优惠券和红包之后的价格
            //BigDecimal subtract = totalPrice.subtract(orderRequest.getDiscount().getMoney());
            //改变优惠券和红包的状态
            order.setTotalAmount(totalPrice);

            orderMapper.insertOrder(order);
            //保存订单详情
            orderItemList.forEach(orderItemRequest -> {
                OrderItem orderItem = new OrderItem();
                BeanUtils.copyProperties(orderItemRequest,orderItem);
                //设置订单号
                orderItem.setOrderNo(order.getOrderSn());
                orderItem.setOrderId(order.getOrderId());
                orderItemMapper.insert(orderItem);
            });

            //清除购物车 -- 通过用户id和商品id清除
            List<Integer> integerList = carsMapper.updateCars(orderRequest.getUserId(), ids);

            //确认订单
            //订单  商品信息
            orderVO.setOrderSn(order.getOrderSn());
            orderVO.setSubject("xx商品");
            orderVO.setTotalPrice(totalPrice);
        } catch (Exception e){
            throw new RuntimeException("系统异常");
        }
        return RequestEntity.success(orderVO);
    }


    /**
     * 对比价格是否发生了改变
     * @param products
     * @param orderItemList
     * @return true表示价格发生改变，false表示正常
     */
    private boolean isPriceChange(List<Product> products, List<OrderItemRequestParam> orderItemList) {
        for (int i = 0; i < products.size(); i++) {
            for (int j = 0; j < orderItemList.size(); j++) {
                //如果查询的价格与用户购买时的价格不一致
                if (!products.get(i).getPrice().equals(orderItemList.get(i).getPrice())){
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 查询商品库存是否足够
     * @param orderItemList
     * @return
     */
    private boolean isEnough(List<OrderItemRequestParam> orderItemList) {
        //获取商品id
        List<Integer> ids = new ArrayList<>();
        orderItemList.forEach(orderItem->{
            ids.add(orderItem.getProductId());
        });
        //查询商品库存数量
        List<ProductStock> productStocks = productStockMapper.selectProductSize(ids);
        for (int i = 0; i < productStocks.size(); i++) {
            //如果库存数量小于用户购买数量
            if ((productStocks.get(i).getTotal()-productStocks.get(i).getStock()) < orderItemList.get(i).getQuantity()){
                return true;
            }
        }
        return false;
    }
}
