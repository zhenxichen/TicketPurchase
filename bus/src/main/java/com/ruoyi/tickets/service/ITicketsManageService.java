package com.ruoyi.tickets.service;

import com.ruoyi.tickets.domain.DTO.Driver;
import com.ruoyi.tickets.domain.Tickets;
import com.ruoyi.tickets.domain.dao.BusIdAndDateDAO;

import java.util.Date;
import java.util.List;

/**
 * 车票量管理 服务层接口
 *
 * @author Zhenxi Chen
 * @date 2021/6/10
 */
public interface ITicketsManageService {
    /**
     * 通过车次和日期获取车票量信息
     *
     * @param busId 车次
     * @param busDate 日期
     * @return
     */
    Tickets selectTicketsByIdAndDate(String busId, Date busDate);

    /**
     * 设置某个日期的车票量
     *
     * @param tickets 车票量数据
     * @return
     */
    int setTickets(Tickets tickets);

    /**
     * 获取司机列表
     * @return
     */
    List<Driver> selectDriverList();
}