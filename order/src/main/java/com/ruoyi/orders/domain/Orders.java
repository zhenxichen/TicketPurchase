package com.ruoyi.orders.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 订单对象 orders
 * 
 * @author ruoyi
 * @date 2021-06-05
 */
public class Orders extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 订单号 */
    @Excel(name = "订单号")
    private Long orderId;

    /** 用户ID */
    @Excel(name = "用户ID")
    private Long userId;

    /** 订单状态（0待支付 1待核销 2已完成 3已关闭） */
    @Excel(name = "订单状态", readConverterExp = "0=待支付,1=待核销,2=已完成,3=已关闭")
    private String status;

    /** 车次 */
    @Excel(name = "车次")
    private String bus;

    /** 日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date date;

    /** 核销时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "核销时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date verifyTime;

    public void setOrderId(Long orderId) 
    {
        this.orderId = orderId;
    }

    public Long getOrderId() 
    {
        return orderId;
    }
    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setBus(String bus) 
    {
        this.bus = bus;
    }

    public String getBus() 
    {
        return bus;
    }
    public void setDate(Date date) 
    {
        this.date = date;
    }

    public Date getDate() 
    {
        return date;
    }
    public void setVerifyTime(Date verifyTime) 
    {
        this.verifyTime = verifyTime;
    }

    public Date getVerifyTime() 
    {
        return verifyTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("orderId", getOrderId())
            .append("userId", getUserId())
            .append("status", getStatus())
            .append("bus", getBus())
            .append("date", getDate())
            .append("createTime", getCreateTime())
            .append("verifyTime", getVerifyTime())
            .toString();
    }
}
