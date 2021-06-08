package com.ruoyi.tickets.service;

import com.ruoyi.tickets.domain.DTO.TicketDTO;
import com.ruoyi.tickets.domain.TicketOrder;

import javax.servlet.http.HttpServletRequest;
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
     * 购票，返回订单id
     * @param ticketOrder
     * @return
     */
    public String purchaseTicket(TicketOrder ticketOrder,Long userId,Long userRole);
}
