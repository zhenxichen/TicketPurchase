package com.ruoyi.orders.service;

/**
 * 管理订单状态的服务层接口
 *
 * @author Zhenxi Chen
 * @date 2021/6/17
 */
public interface IOrderStatusService {
    /**
     * 判断订单是否已经支付状态
     *
     * @param orderId 订单号
     * @return true if the order is paid, false for otherwise.
     */
    boolean isOrderPay(String orderId);

    /**
     * 关闭订单
     *
     * @param orderId 订单号
     */
    int closeOrder(String orderId);
}
