package com.ruoyi.orders.domain;

import java.util.Date;

/**
 * 用于数据库查询参数
 *
 * @author Mei Huang
 * @date 2021/6/8
 */
public class OrdersInfo {

    private Long userId;      //用户编号
    private String orderID;     //订单编号
    private Date date;        //发车日期
    private String start;       //发车站
    private String dest;        //目的站

    public OrdersInfo() {
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
