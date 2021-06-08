package com.ruoyi.orders.domain;

/**
 * 用于前端传来的order数据信息（存储与json中）
 *
 * @author Mei Huang
 * @date 2021/6/8
 */
public class OrderInfo {
    private String orderID;     //订单编号
    private String date;        //发车日期
    private String start;       //发车站
    private String dest;        //目的站

    public OrderInfo() {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }
}
