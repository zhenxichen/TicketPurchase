package com.ruoyi.orders.domain;

import java.math.BigDecimal;

public class WechatOrder {
    private String orderId;
    private double price;
    private String orderInformation;
    private String openid;

    public WechatOrder() {
    }

    public WechatOrder(String orderId, double price, String orderInformation, String openid) {
        this.orderId = orderId;
        this.price = price;
        this.orderInformation = orderInformation;
        this.openid = openid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getOrderInformation() {
        return orderInformation;
    }

    public void setOrderInformation(String orderInformation) {
        this.orderInformation = orderInformation;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
