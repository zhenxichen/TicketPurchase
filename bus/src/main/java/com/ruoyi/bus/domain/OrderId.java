package com.ruoyi.bus.domain;

/**
 * 用于读取前端传来的OrderId数据（存储于json当中）
 * @author Zhenxi Chen
 * @date 2021/6/5
 */
public class OrderId {
    private String orderID;

    public String getOrderID() {
        return orderID;
    }

    public void setOrderId(String orderId) {
        this.orderID = orderId;
    }
}
