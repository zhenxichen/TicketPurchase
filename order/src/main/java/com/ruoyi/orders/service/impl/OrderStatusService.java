package com.ruoyi.orders.service.impl;

import com.ruoyi.orders.mapper.OrdersMapper;
import com.ruoyi.orders.service.IOrderStatusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 订单状态的服务层接口
 */
@Service
public class OrderStatusService implements IOrderStatusService {

    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 判断订单是否已经支付状态
     *
     * @param orderId 订单号
     * @return true if the order is paid, false for otherwise.
     */
    @Override
    public boolean isOrderPay(String orderId) {
        return ordersMapper.checkOrderIsPaid(orderId) == 0;
    }

    /**
     * 关闭订单
     *
     * @param orderId 订单号
     */
    @Override
    public int closeOrder(String orderId) {
        return ordersMapper.closeOrder(orderId);
    }
}
