package com.ruoyi.station.mapper;

import com.ruoyi.station.domain.Station;

public interface StationExtendMapper {
    /**
     * 根据名称查询车站
     * @param stationName 车站名称
     * @return
     */
    public Station selectStationByName(String stationName);
}
