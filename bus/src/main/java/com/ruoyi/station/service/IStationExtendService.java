package com.ruoyi.station.service;

import com.ruoyi.station.domain.Station;
import com.ruoyi.station.domain.dto.StationDTO;
import com.ruoyi.station.domain.dto.StationListDTO;

import java.util.List;

public interface IStationExtendService {
    /**
     * 根据车站名称查询车站id
     *
     * @param stationName 车站ID
     * @return 车站
     */
    public Station selectStationByName(String stationName);
    /**
     * 查询车站列表
     *
     * @param station 车站
     * @return 车站集合
     */
    public List<StationListDTO> selectStationList(Station station);
}
