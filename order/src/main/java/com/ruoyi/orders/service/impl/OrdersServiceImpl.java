package com.ruoyi.orders.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.orders.domain.OrderInfo;
import com.ruoyi.orders.domain.OrdersInfo;
import com.ruoyi.orders.domain.dto.SellCodeDTO;
import com.ruoyi.orders.domain.dto.UserOrdersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.orders.mapper.OrdersMapper;
import com.ruoyi.orders.domain.Orders;
import com.ruoyi.orders.service.IOrdersService;

/**
 * 订单Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-06
 */
@Service
public class OrdersServiceImpl implements IOrdersService 
{
    @Autowired
    private OrdersMapper ordersMapper;

    /**
     * 查询订单
     * 
     * @param orderId 订单ID
     * @return 订单
     */
    @Override
    public Orders selectOrdersById(String orderId)
    {
        return ordersMapper.selectOrdersById(orderId);
    }

    /**
     * 查询订单列表
     * 
     * @param orders 订单
     * @return 订单
     */
    @Override
    public List<Orders> selectOrdersList(Orders orders)
    {
        return ordersMapper.selectOrdersList(orders);
    }

    /**
     * 新增订单
     * 
     * @param orders 订单
     * @return 结果
     */
    @Override
    public int insertOrders(Orders orders)
    {
        orders.setCreateTime(DateUtils.getNowDate());
        return ordersMapper.insertOrders(orders);
    }

    /**
     * 修改订单
     * 
     * @param orders 订单
     * @return 结果
     */
    @Override
    public int updateOrders(Orders orders)
    {
        return ordersMapper.updateOrders(orders);
    }

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的订单ID
     * @return 结果
     */
    @Override
    public int deleteOrdersByIds(String[] orderIds)
    {
        return ordersMapper.deleteOrdersByIds(orderIds);
    }

    /**
     * 删除订单信息
     * 
     * @param orderId 订单ID
     * @return 结果
     */
    @Override
    public int deleteOrdersById(String orderId)
    {
        return ordersMapper.deleteOrdersById(orderId);
    }

    /**
    * @Description
    * @param orderID 订单ID
    * @return
    * @author Mei Huang
    * @date 2021/6/6
    */
    @Override
    public SellCodeDTO getSellCode(String orderID){
        SellCodeDTO res = new SellCodeDTO();
        res.setOrderId(orderID);
        String ticketStatus = ordersMapper.selectStatusById(orderID);
        res.setTicketStatus(ticketStatus);
        return res;
    }

    /**
     * @Description 获取普通用户订单列表
     * @param userOrder
     * @return 结果list
     * @author Mei Huang
     * @date 2021/6/8
     */
    @Override
    public  List<UserOrdersDTO> selectNormalOrdersList(OrdersInfo userOrder){
        List<UserOrdersDTO> resList = ordersMapper.selectNormalOrdersList(userOrder);
        return resList;
    }

    /**
     * @Description 获取员工用户订单列表
     * @param userOrder
     * @return 结果list
     * @author Mei Huang
     * @date 2021/6/8
     */
    @Override
    public  List<UserOrdersDTO> selectEmployeeOrdersList(OrdersInfo userOrder){
        List<UserOrdersDTO> resList = ordersMapper.selectEmployeeOrdersList(userOrder);
        return resList;
    }
}
