package com.ruoyi.station.domain.dto;
/**
 * 用于向前端传递核车站信息的DTO
 * @author duan
 * @date 2021/6/7
 */
public class StationDTO {
    private Long stationID;       // 总核销人数

    private String stationName;      // 已核销人数
    public Long getStationID() {
        return stationID;
    }

    public void setStationID(Long stationID) {
        this.stationID = stationID;
    }

    public String getStationName() {
        return stationName;
    }

    public void setStationName(String stationName) {
        this.stationName = stationName;
    }

    public StationDTO(Long stationID, String stationName) {
        this.stationID = stationID;
        this.stationName = stationName;
    }


}
