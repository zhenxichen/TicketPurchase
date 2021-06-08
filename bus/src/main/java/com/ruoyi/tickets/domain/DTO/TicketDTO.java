package com.ruoyi.tickets.domain.DTO;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class TicketDTO {
    /** 车次 */
    private String busId;
    /**出发车站*/
    private String startStation;
    /**到达车站*/
    private String destStation;
    /** 发车日期 */
    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT+8")
    private Date busDate;
    /** 出发时间 */
    @JsonFormat(pattern="kk:mm:ss",timezone = "GMT+8")
    private Date startTime;
    /** 结束时间 */
    @JsonFormat(pattern="kk:mm:ss",timezone = "GMT+8")
    private Date endTime;
    /** 员工票价格 */
    private Long employeePrice;
    /** 普通票价格 */
    private Long normalPrice;
    /** 剩余员工票数量 */
    private Long employeeTicketsRemain;
    /** 剩余普通票数量 */
    private Long normalTicketsRemain;

    public TicketDTO(String busId, String startStation, String destStation, Date busDate, Date startTime, Date endTime, Long employeePrice, Long normalPrice, Long employeeTicketsRemain, Long normalTicketsRemain) {
        this.busId = busId;
        this.startStation = startStation;
        this.destStation = destStation;
        this.busDate = busDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employeePrice = employeePrice;
        this.normalPrice = normalPrice;
        this.employeeTicketsRemain = employeeTicketsRemain;
        this.normalTicketsRemain = normalTicketsRemain;
    }

    public String getBusId() {
        return busId;
    }

    public void setBusId(String busId) {
        this.busId = busId;
    }

    public String getStartStation() {
        return startStation;
    }

    public void setStartStation(String startStation) {
        this.startStation = startStation;
    }

    public String getDestStation() {
        return destStation;
    }

    public void setDestStation(String destStation) {
        this.destStation = destStation;
    }

    public Date getBusDate() {
        return busDate;
    }

    public void setBusDate(Date busDate) {
        this.busDate = busDate;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Long getEmployeePrice() {
        return employeePrice;
    }

    public void setEmployeePrice(Long employeePrice) {
        this.employeePrice = employeePrice;
    }

    public Long getNormalPrice() {
        return normalPrice;
    }

    public void setNormalPrice(Long normalPrice) {
        this.normalPrice = normalPrice;
    }

    public Long getEmployeeTicketsRemain() {
        return employeeTicketsRemain;
    }

    public void setEmployeeTicketsRemain(Long employeeTicketsRemain) {
        this.employeeTicketsRemain = employeeTicketsRemain;
    }

    public Long getNormalTicketsRemain() {
        return normalTicketsRemain;
    }

    public void setNormalTicketsRemain(Long normalTicketsRemain) {
        this.normalTicketsRemain = normalTicketsRemain;
    }
}
