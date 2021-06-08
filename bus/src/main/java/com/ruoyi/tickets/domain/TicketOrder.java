package com.ruoyi.tickets.domain;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 用于在购买车票接口接收前端传来的数据
 */
public class TicketOrder {
    private String busId;
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date startDate;
    @JsonFormat(pattern="kk:mm:ss",timezone = "GMT+8")
    private Date startTime;

    public TicketOrder() {
    }

    public TicketOrder(String busId, Date startDate, Date startTime) {
        this.busId = busId;
        this.startDate = startDate;
        this.startTime = startTime;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    @Override
    public String toString() {
        return "TicketOrder{" +
                "busId='" + busId + '\'' +
                ", startDate=" + startDate +
                ", startTime=" + startTime +
                '}';
    }
}
