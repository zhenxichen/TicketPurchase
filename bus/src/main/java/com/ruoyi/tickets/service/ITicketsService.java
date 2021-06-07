package com.ruoyi.tickets.service;

import java.util.List;
import com.ruoyi.tickets.domain.Tickets;

/**
 * 车票管理Service接口
 * 
 * @author ruoyi
 * @date 2021-06-07
 */
public interface ITicketsService 
{
    /**
     * 查询车票管理
     * 
     * @param busId 车票管理ID
     * @return 车票管理
     */
    public Tickets selectTicketsById(String busId);

    /**
     * 查询车票管理列表
     * 
     * @param tickets 车票管理
     * @return 车票管理集合
     */
    public List<Tickets> selectTicketsList(Tickets tickets);

    /**
     * 新增车票管理
     * 
     * @param tickets 车票管理
     * @return 结果
     */
    public int insertTickets(Tickets tickets);

    /**
     * 修改车票管理
     * 
     * @param tickets 车票管理
     * @return 结果
     */
    public int updateTickets(Tickets tickets);

    /**
     * 批量删除车票管理
     * 
     * @param busIds 需要删除的车票管理ID
     * @return 结果
     */
    public int deleteTicketsByIds(String[] busIds);

    /**
     * 删除车票管理信息
     * 
     * @param busId 车票管理ID
     * @return 结果
     */
    public int deleteTicketsById(String busId);
}
