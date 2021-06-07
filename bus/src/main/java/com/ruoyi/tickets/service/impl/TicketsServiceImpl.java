package com.ruoyi.tickets.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.tickets.mapper.TicketsMapper;
import com.ruoyi.tickets.domain.Tickets;
import com.ruoyi.tickets.service.ITicketsService;

/**
 * 车票管理Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-06-07
 */
@Service
public class TicketsServiceImpl implements ITicketsService 
{
    @Autowired
    private TicketsMapper ticketsMapper;

    /**
     * 查询车票管理
     * 
     * @param busId 车票管理ID
     * @return 车票管理
     */
    @Override
    public Tickets selectTicketsById(String busId)
    {
        return ticketsMapper.selectTicketsById(busId);
    }

    /**
     * 查询车票管理列表
     * 
     * @param tickets 车票管理
     * @return 车票管理
     */
    @Override
    public List<Tickets> selectTicketsList(Tickets tickets)
    {
        return ticketsMapper.selectTicketsList(tickets);
    }

    /**
     * 新增车票管理
     * 
     * @param tickets 车票管理
     * @return 结果
     */
    @Override
    public int insertTickets(Tickets tickets)
    {
        return ticketsMapper.insertTickets(tickets);
    }

    /**
     * 修改车票管理
     * 
     * @param tickets 车票管理
     * @return 结果
     */
    @Override
    public int updateTickets(Tickets tickets)
    {
        return ticketsMapper.updateTickets(tickets);
    }

    /**
     * 批量删除车票管理
     * 
     * @param busIds 需要删除的车票管理ID
     * @return 结果
     */
    @Override
    public int deleteTicketsByIds(String[] busIds)
    {
        return ticketsMapper.deleteTicketsByIds(busIds);
    }

    /**
     * 删除车票管理信息
     * 
     * @param busId 车票管理ID
     * @return 结果
     */
    @Override
    public int deleteTicketsById(String busId)
    {
        return ticketsMapper.deleteTicketsById(busId);
    }
}
