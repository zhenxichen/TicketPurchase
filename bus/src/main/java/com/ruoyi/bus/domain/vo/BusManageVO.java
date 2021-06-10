package com.ruoyi.bus.domain.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class BusManageVO {
    /** 车次 */
    private String busId;

    /** 始发站 */
    @Excel(name = "始发站")
    private String start;

    /** 终点站 */
    @Excel(name = "终点站编号")
    private String dest;

    /** 发车时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "发车时间", width = 30, dateFormat = "HH:mm:ss")
    private Date startTime;

    /** 到达时间 */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @Excel(name = "到达时间", width = 30, dateFormat = "HH:mm:ss")
    private Date endTime;

    /** 间隔天数 */
    @Excel(name = "间隔天数")
    private Long day;

    /** 默认座位数 */
    @Excel(name = "默认座位数")
    private Long seat;

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
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

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getDay() {
        return day;
    }

    public void setDay(Long day) {
        this.day = day;
    }

    public Long getSeat() {
        return seat;
    }

    public void setSeat(Long seat) {
        this.seat = seat;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("busId", getBusId())
                .append("start", getStart())
                .append("dest", getDest())
                .append("startTime", getStartTime())
                .append("endTime", getEndTime())
                .append("day", getDay())
                .append("seat", getSeat())
                .toString();
    }
}
