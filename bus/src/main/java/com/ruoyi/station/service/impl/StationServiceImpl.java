package com.ruoyi.station.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.station.mapper.StationMapper;
import com.ruoyi.station.domain.Station;
import com.ruoyi.station.service.IStationService;

/**
 * 车站Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-07
 */
@Service
public class StationServiceImpl implements IStationService 
{
    @Autowired
    private StationMapper stationMapper;

    /**
     * 查询车站
     * 
     * @param stationId 车站ID
     * @return 车站
     */
    @Override
    public Station selectStationById(Long stationId)
    {
        return stationMapper.selectStationById(stationId);
    }

    /**
     * 查询车站列表
     * 
     * @param station 车站
     * @return 车站
     */
    @Override
    public List<Station> selectStationList(Station station)
    {
        return
                stationMapper.selectStationList(station);
    }

    /**
     * 新增车站
     * 
     * @param station 车站
     * @return 结果
     */
    @Override
    public int insertStation(Station station)
    {
        return stationMapper.insertStation(station);
    }

    /**
     * 修改车站
     * 
     * @param station 车站
     * @return 结果
     */
    @Override
    public int updateStation(Station station)
    {
        return stationMapper.updateStation(station);
    }

    /**
     * 批量删除车站
     * 
     * @param stationIds 需要删除的车站ID
     * @return 结果
     */
    @Override
    public int deleteStationByIds(Long[] stationIds)
    {
        return stationMapper.deleteStationByIds(stationIds);
    }

    /**
     * 删除车站信息
     * 
     * @param stationId 车站ID
     * @return 结果
     */
    @Override
    public int deleteStationById(Long stationId)
    {
        return stationMapper.deleteStationById(stationId);
    }
}
