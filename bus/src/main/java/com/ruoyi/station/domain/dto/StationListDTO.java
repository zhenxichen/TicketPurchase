package com.ruoyi.station.domain.dto;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

public class StationListDTO {
    private Character prefix;
    private List<StationDTO> stationList;

    public StationListDTO(Character prefix) {
        this.prefix = prefix;
        this.stationList=new ArrayList<StationDTO>();
    }

    public Character getPrefix() {
        return prefix;
    }

    public void setPrefix(Character prefix) {
        this.prefix = prefix;
    }

    public List<StationDTO> getStationList() {
        return stationList;
    }

    public void setStationList(List<StationDTO> stationList) {
        this.stationList = stationList;
    }
}
