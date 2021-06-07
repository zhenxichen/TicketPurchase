package com.ruoyi.station.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车站对象 station
 * 
 * @author ruoyi
 * @date 2021-06-07
 */
public class Station extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车站ID */
    private Long stationId;

    /** 车站名称 */
    @Excel(name = "车站名称")
    private String stationName;

    public void setStationId(Long stationId) 
    {
        this.stationId = stationId;
    }

    public Long getStationId() 
    {
        return stationId;
    }
    public void setStationName(String stationName) 
    {
        this.stationName = stationName;
    }

    public String getStationName() 
    {
        return stationName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("stationId", getStationId())
            .append("stationName", getStationName())
            .toString();
    }
}
