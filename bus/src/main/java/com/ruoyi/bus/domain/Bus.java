package com.ruoyi.bus.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车次管理对象 bus
 * 
 * @author ruoyi
 * @date 2021-06-05
 */
public class Bus extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车次 */
    private String busId;

    /** 始发站编号 */
    @Excel(name = "始发站编号")
    private Long start;

    /** 终点站编号 */
    @Excel(name = "终点站编号")
    private Long dest;

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

    public void setBusId(String busId) 
    {
        this.busId = busId;
    }

    public String getBusId() 
    {
        return busId;
    }
    public void setStart(Long start) 
    {
        this.start = start;
    }

    public Long getStart() 
    {
        return start;
    }
    public void setDest(Long dest) 
    {
        this.dest = dest;
    }

    public Long getDest() 
    {
        return dest;
    }
    public void setStartTime(Date startTime) 
    {
        this.startTime = startTime;
    }

    public Date getStartTime() 
    {
        return startTime;
    }
    public void setEndTime(Date endTime) 
    {
        this.endTime = endTime;
    }

    public Date getEndTime() 
    {
        return endTime;
    }
    public void setDay(Long day) 
    {
        this.day = day;
    }

    public Long getDay() 
    {
        return day;
    }
    public void setSeat(Long seat) 
    {
        this.seat = seat;
    }

    public Long getSeat() 
    {
        return seat;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
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
