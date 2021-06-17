package com.ruoyi.orders.service.impl;

import com.ruoyi.orders.domain.Orders;
import com.ruoyi.orders.mapper.OrdersMapper;
import com.ruoyi.orders.service.IOrdersExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class IOrdersExtendServiceImpl implements IOrdersExtendService {
    @Autowired
    private OrdersMapper ordersMapper;
    @Override
    public void setOrderStatus(String orderId,String status) {
        Orders order=ordersMapper.selectOrdersById(orderId);
        order.setStatus(status);
        ordersMapper.updateOrders(order);
        return;

    }
}
