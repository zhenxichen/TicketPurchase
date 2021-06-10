package com.ruoyi.tickets.service.impl;

import com.ruoyi.tickets.domain.DTO.Driver;
import com.ruoyi.tickets.domain.Tickets;
import com.ruoyi.tickets.domain.dao.BusIdAndDateDAO;
import com.ruoyi.tickets.mapper.TicketsManageMapper;
import com.ruoyi.tickets.mapper.TicketsMapper;
import com.ruoyi.tickets.service.ITicketsManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 车票量管理 服务层
 * @author Zhenxi Chen
 * @date 2021/6/10
 */
@Service
public class TicketsManageServiceImpl implements ITicketsManageService {

    @Autowired
    TicketsMapper ticketsMapper;

    @Autowired
    TicketsManageMapper ticketsManageMapper;

    /**
     * 通过车次和日期获取车票量信息
     *
     * @param busId   车次
     * @param busDate 日期
     * @return
     */
    @Override
    public Tickets selectTicketsByIdAndDate(String busId, Date busDate) {
        BusIdAndDateDAO dao = new BusIdAndDateDAO();
        dao.setBusId(busId);
        dao.setBusDate(busDate);
        return ticketsManageMapper.selectTicketsByIdAndDate(dao);
    }

    /**
     * 设置某个日期的车票量
     *
     * @param tickets 车票量数据
     * @return
     */
    @Override
    public int setTickets(Tickets tickets) {
        Tickets t = this.selectTicketsByIdAndDate(tickets.getBusId(), tickets.getBusDate());
        if (t == null) {
            return ticketsMapper.insertTickets(tickets);
        }
        return ticketsMapper.updateTickets(tickets);
    }

    /**
     * 获取司机列表
     *
     * @return
     */
    @Override
    public List<Driver> selectDriverList() {
        return ticketsManageMapper.selectDriverList();
    }

}
