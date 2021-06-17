package com.ruoyi.orders.domain;

import java.math.BigDecimal;

public class WechatOrder {
    private String orderId;
    private BigDecimal fee;
    private String orderInformation;
    private String openid;

    public WechatOrder(String orderId, BigDecimal fee, String orderInformation, String openid) {
        this.orderId = orderId;
        this.fee = fee;
        this.orderInformation = orderInformation;
        this.openid = openid;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
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
