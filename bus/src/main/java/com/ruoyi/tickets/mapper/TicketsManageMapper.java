package com.ruoyi.tickets.mapper;

import com.ruoyi.tickets.domain.DTO.Driver;
import com.ruoyi.tickets.domain.Tickets;
import com.ruoyi.tickets.domain.dao.BusIdAndDateDAO;

import java.util.List;

/**
 * 车票量管理 数据层接口
 */
public interface TicketsManageMapper {
    /**
     * 通过车次和日期获取车票量信息
     * @param dao
     * @return
     */
    Tickets selectTicketsByIdAndDate(BusIdAndDateDAO dao);

    /**
     * 获取司机列表
     * @return
     */
    List<Driver> selectDriverList();
}
