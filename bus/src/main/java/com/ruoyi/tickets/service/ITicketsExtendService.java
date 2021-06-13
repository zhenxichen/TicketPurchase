package com.ruoyi.tickets.service;

import com.ruoyi.tickets.domain.DTO.TicketDTO;
import com.ruoyi.tickets.domain.TicketOrder;

import java.util.List;

/**
 * 车票管理扩展接口
 */
public interface ITicketsExtendService {
    /**
     * 根据条件查询车票
     * @param start
     * @param dest
     * @param date
     * @return
     */
    public List<TicketDTO> queryTickets(String start, String dest, String date);

    /**
     * 购票，返回订单id。订单状态未支付
     * 员工优先买员工票，没有员工票可以买普通票
     * 普通用户只能买普通用户
     * 购票成功返回订单id
     * 购票失败返回null
     * 目前防错机制不太完善
     * @param ticketOrder
     * @return
     */
    public String purchaseTicket(TicketOrder ticketOrder,Long userId,Long userRole);

    /**
     * 付款
     * @param orderId
     * @param userId
     * @param userRole
     * @return
     */
    public boolean payOrder(String orderId,Long userId,Long userRole);
}
