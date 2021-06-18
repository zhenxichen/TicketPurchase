package com.ruoyi.tickets.domain.dao;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * 封装车次和日期，用于访问数据库tickets表
 * @author Zhenxi Chen
 * @date 2021/6/10
 */
public class BusIdAndDateDAO {

    private String busId;       // 车次

    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date busDate;       // 日期

    public BusIdAndDateDAO() {
    }

    public BusIdAndDateDAO(String busId, Date busDate) {
        this.busId = busId;
        this.busDate = busDate;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public Date getBusDate() {
        return busDate;
    }

    public void setBusDate(Date busDate) {
        this.busDate = busDate;
    }
}
