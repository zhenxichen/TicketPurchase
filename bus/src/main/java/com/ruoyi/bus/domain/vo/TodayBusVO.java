package com.ruoyi.bus.domain.vo;

/**
 * 用于向前端返回司机今日车次信息的VO
 * @author Zhenxi Chen
 * @date 2021/6/6
 */
public class TodayBusVO {
    private String busID;       // 车次

    private String start;       // 起点

    private String dest;        // 终点

    private String date;        // 发车日期

    private String startTime;       // 发车时间

    private String destTime;         // 到达时间

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
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
}
