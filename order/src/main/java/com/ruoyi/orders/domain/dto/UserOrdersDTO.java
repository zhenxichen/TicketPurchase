package com.ruoyi.orders.domain.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 用于向前端传递用户orders数据的DTO
 *
 * @author Mei Huang
 * @date 2021/6/8
 */
public class UserOrdersDTO {
    private String orderID;         //订单编号

    private String busID;           //车次编号

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date;              //发车日期
    
    private String startTime;       //发车时间
    private String destTime;        //到站时间
    private String start;           //发车站
    private String dest;            //目的站
    private double price;           //车票价格
    private String ticketStatus;    //订单状态

    public String getOrderID() {
        return orderID;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getDestTime() {
        return destTime;
    }

    public void setDestTime(String destTime) {
        this.destTime = destTime;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getDest() {
        return dest;
    }

    public void setDest(String dest) {
        this.dest = dest;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
