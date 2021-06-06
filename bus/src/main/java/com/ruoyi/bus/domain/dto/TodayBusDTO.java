package com.ruoyi.bus.domain.dto;

import java.util.Date;

/**
 * 用于存储向数据库返回的今日车次信息的DTO
 * @author Zhenxi Chen
 * @date 2021/6/5
 */
public class TodayBusDTO {
    private String busID;       // 车次

    private String start;          // 起点站名称

    private String dest;            // 终点站编号

    private Date date;          // 发车日期

    private Date startTime;       // 发车时间

    private Date destTime;        // 到达时间

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getDestTime() {
        return destTime;
    }

    public void setDestTime(Date destTime) {
        this.destTime = destTime;
    }
}
