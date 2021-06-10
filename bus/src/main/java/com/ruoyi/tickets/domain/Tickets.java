package com.ruoyi.tickets.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 车票管理对象 tickets
 * 
 * @author ruoyi
 * @date 2021-06-07
 */
public class Tickets extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 车次 */
    private String busId;

    /** 发车日期 */
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    private Date busDate;

    /** 座位数 */
    @Excel(name = "座位数")
    private Long seat;

    /** 员工票数量 */
    @Excel(name = "员工票数量")
    private Long employeeTickets;

    /** 普通票数量 */
    @Excel(name = "普通票数量")
    private Long normalTickets;

    /** 剩余员工票数量 */
    @Excel(name = "剩余员工票数量")
    private Long employeeTicketsRemain;

    /** 剩余普通票数量 */
    @Excel(name = "剩余普通票数量")
    private Long normalTicketsRemain;

    /** 员工票价格 */
    @Excel(name = "员工票价格")
    private Long employeePrice;

    /** 普通票价格 */
    @Excel(name = "普通票价格")
    private Long normalPrice;

    /** 司机  */
    private Long driver;

    public void setBusId(String busId) 
    {
        this.busId = busId;
    }

    public String getBusId() 
    {
        return busId;
    }
    public void setBusDate(Date busDate) 
    {
        this.busDate = busDate;
    }

    public Date getBusDate() 
    {
        return busDate;
    }
    public void setSeat(Long seat) 
    {
        this.seat = seat;
    }

    public Long getSeat() 
    {
        return seat;
    }
    public void setEmployeeTickets(Long employeeTickets) 
    {
        this.employeeTickets = employeeTickets;
    }

    public Long getEmployeeTickets() 
    {
        return employeeTickets;
    }
    public void setNormalTickets(Long normalTickets) 
    {
        this.normalTickets = normalTickets;
    }

    public Long getNormalTickets() 
    {
        return normalTickets;
    }
    public void setEmployeeTicketsRemain(Long employeeTicketsRemain) 
    {
        this.employeeTicketsRemain = employeeTicketsRemain;
    }

    public Long getEmployeeTicketsRemain() 
    {
        return employeeTicketsRemain;
    }
    public void setNormalTicketsRemain(Long normalTicketsRemain) 
    {
        this.normalTicketsRemain = normalTicketsRemain;
    }

    public Long getNormalTicketsRemain() 
    {
        return normalTicketsRemain;
    }
    public void setEmployeePrice(Long employeePrice) 
    {
        this.employeePrice = employeePrice;
    }

    public Long getEmployeePrice() 
    {
        return employeePrice;
    }
    public void setNormalPrice(Long normalPrice) 
    {
        this.normalPrice = normalPrice;
    }

    public Long getNormalPrice() 
    {
        return normalPrice;
    }

    public Long getDriver() {
        return driver;
    }

    public void setDriver(Long driver) {
        this.driver = driver;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("busId", getBusId())
            .append("busDate", getBusDate())
            .append("seat", getSeat())
            .append("employeeTickets", getEmployeeTickets())
            .append("normalTickets", getNormalTickets())
            .append("employeeTicketsRemain", getEmployeeTicketsRemain())
            .append("normalTicketsRemain", getNormalTicketsRemain())
            .append("employeePrice", getEmployeePrice())
            .append("normalPrice", getNormalPrice())
            .toString();
    }
}
