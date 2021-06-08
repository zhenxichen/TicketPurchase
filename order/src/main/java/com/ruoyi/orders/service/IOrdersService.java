package com.ruoyi.orders.service;

import java.util.List;
import com.ruoyi.orders.domain.Orders;
import com.ruoyi.orders.domain.OrderInfo;
import com.ruoyi.orders.domain.OrdersInfo;
import com.ruoyi.orders.domain.dto.SellCodeDTO;
import com.ruoyi.orders.domain.dto.UserOrdersDTO;

/**
 * 订单Service接口
 * 
 * @author ruoyi
 * @date 2021-06-06
 */
public interface IOrdersService 
{
    /**
     * 查询订单
     * 
     * @param orderId 订单ID
     * @return 订单
     */
    public Orders selectOrdersById(String orderId);

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
    public int deleteOrdersByIds(String[] orderIds);

    /**
     * 删除订单信息
     * 
     * @param orderId 订单ID
     * @return 结果
     */
    public int deleteOrdersById(String orderId);

    /**
     * @Description 获取订单核销状态
     * @param orderID 订单ID
     * @return 结果
     * @author Mei Huang
     * @date 2021/6/6
     */
    SellCodeDTO getSellCode(String orderID);

    /**
    * @Description 获取普通用户订单列表
    * @param userOrder
    * @return 结果list
    * @author Mei Huang
    * @date 2021/6/8
    */
    List<UserOrdersDTO> selectNormalOrdersList(OrdersInfo userOrder);

    /**
     * @Description 获取员工用户订单列表
     * @param userOrder
     * @return 结果list
     * @author Mei Huang
     * @date 2021/6/8
     */
    List<UserOrdersDTO> selectEmployeeOrdersList(OrdersInfo userOrder);
}
