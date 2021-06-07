package com.ruoyi.station.service;

import java.util.List;
import com.ruoyi.station.domain.Station;

/**
 * 车站Service接口
 * 
 * @author ruoyi
 * @date 2021-06-07
 */
public interface IStationService 
{
    /**
     * 查询车站
     * 
     * @param stationId 车站ID
     * @return 车站
     */
    public Station selectStationById(Long stationId);

    /**
     * 查询车站列表
     * 
     * @param station 车站
     * @return 车站集合
     */
    public List<Station> selectStationList(Station station);

    /**
     * 新增车站
     * 
     * @param station 车站
     * @return 结果
     */
    public int insertStation(Station station);

    /**
     * 修改车站
     * 
     * @param station 车站
     * @return 结果
     */
    public int updateStation(Station station);

    /**
     * 批量删除车站
     * 
     * @param stationIds 需要删除的车站ID
     * @return 结果
     */
    public int deleteStationByIds(Long[] stationIds);

    /**
     * 删除车站信息
     * 
     * @param stationId 车站ID
     * @return 结果
     */
    public int deleteStationById(Long stationId);
}
