package com.ruoyi.orders.domain.dto;

/**
 * 用于向前端传递订单核销状态数据的DTO
 *
 * @author Mei Huang
 * @date 2021/6/6
 */
public class SellCodeDTO {
    private String orderId;         // 订单编号

    private String ticketStatus;    // 车票状态信息

    public SellCodeDTO() {
    }

    public SellCodeDTO(String orderId) {
        this.orderId = orderId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getTicketStatus() {
        return ticketStatus;
    }

    public void setTicketStatus(String ticketStatus) {
        this.ticketStatus = ticketStatus;
    }
}
