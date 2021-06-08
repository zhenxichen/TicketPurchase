package com.ruoyi.station.service.impl;

import com.ruoyi.station.domain.ChineseToFirstLetterUtil;
import com.ruoyi.station.domain.Station;
import com.ruoyi.station.domain.dto.StationDTO;
import com.ruoyi.station.domain.dto.StationListDTO;
import com.ruoyi.station.mapper.StationExtendMapper;
import com.ruoyi.station.mapper.StationMapper;
import com.ruoyi.station.service.IStationExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.Collator;
import java.util.*;

@Service
public class IStationExtendServiceImpl implements IStationExtendService {
    @Autowired
    private StationExtendMapper stationExtendMapper;
    @Autowired
    private StationMapper stationMapper;
    /**
     * 根据车站名称查询车站id
     * @param stationName 车站ID
     * @return
     */
    @Override
    public Station selectStationByName(String stationName) {

        return stationExtendMapper.selectStationByName(stationName);
    }
    /**
     * 查询车站列表
     *
     * @param station 车站
     * @return 车站
     */
    @Override
    public List<StationListDTO> selectStationList(Station station)
    {
        List<Station> stationList=stationMapper.selectStationList(station);
        List<StationDTO> stationDTOList=new ArrayList<StationDTO>();
        Map<Character,StationListDTO> stationListDTOMap=new HashMap<Character,StationListDTO>();

        for(Station s :stationList){
            Character prefix=ChineseToFirstLetterUtil.ChineseToFirstLetter(s.getStationName()).charAt(0);
            StationListDTO stationListDTO=stationListDTOMap.get(prefix);
            if (stationListDTO==null){
                stationListDTOMap.put(prefix,new StationListDTO(prefix));
                stationListDTO=stationListDTOMap.get(prefix);
            }
            stationListDTO.getStationList().add(new StationDTO(s.getStationId(),s.getStationName()));
        }
        List<StationListDTO> resList=new ArrayList<>();
        for (StationListDTO s : stationListDTOMap.values()) {
            resList.add(s);
        }
        return resList;

    }
}
