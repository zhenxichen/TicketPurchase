package com.ruoyi.orders.service;

import java.util.List;
import com.ruoyi.orders.domain.Orders;
import com.ruoyi.orders.domain.dto.SellCodeDTO;

/**
 * 订单Service接口
 * 
 * @author ruoyi
 * @date 2021-06-05
 */
public interface IOrdersService 
{
    /**
     * 查询订单
     * 
     * @param orderId 订单ID
     * @return 订单
     */
    public Orders selectOrdersById(Long orderId);

    /**
     * 查询订单列表
     * 
     * @param orders 订单
     * @return 订单集合
     */
    public List<Orders> selectOrdersList(Orders orders);

    /**
     * 新增订单
     * 
     * @param orders 订单
     * @return 结果
     */
    public int insertOrders(Orders orders);

    /**
     * 修改订单
     * 
     * @param orders 订单
     * @return 结果
     */
    public int updateOrders(Orders orders);

    /**
     * 批量删除订单
     * 
     * @param orderIds 需要删除的订单ID
     * @return 结果
     */
    public int deleteOrdersByIds(Long[] orderIds);

    /**
     * 删除订单信息
     * 
     * @param orderId 订单ID
     * @return 结果
     */
    public int deleteOrdersById(Long orderId);

    /**
     * @Description 获取订单核销状态
     * @param orderID 订单ID
     * @return 结果
     * @author Mei Huang
     * @date 2021/6/6
     */
    SellCodeDTO getSellCode(String orderID);
}
