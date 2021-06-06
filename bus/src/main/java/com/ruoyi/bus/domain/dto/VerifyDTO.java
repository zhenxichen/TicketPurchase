package com.ruoyi.bus.domain.dto;

import java.util.Date;

/**
 * 用于存储查询核销记录的消息体
 * @author Zhenxi Chen
 * @date 2021/6/6
 */
public class VerifyDTO {

    private String orderID;     // 订单号

    private String username;    // 用户名

    private String busID;       // 车次

    private Date busDate;       // 日期

    private Date verifyTime;    // 核销时间

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public Date getVerifyTime() {
        return verifyTime;
    }

    public void setVerifyTime(Date verifyTime) {
        this.verifyTime = verifyTime;
    }

    public Date getBusDate() {
        return busDate;
    }

    public void setBusDate(Date busDate) {
        this.busDate = busDate;
    }
}
