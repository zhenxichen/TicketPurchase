package com.ruoyi.tickets.mapper;

import com.ruoyi.tickets.domain.DTO.TicketDTO;
import com.ruoyi.tickets.domain.Tickets;
import org.apache.ibatis.annotations.Param;
import sun.security.krb5.internal.Ticket;

import java.util.Date;
import java.util.List;

public interface TicketsExtendMapper {
    /**
     * 根据车站和开车日期筛选车票
     * @param startStationId
     * @param destStationId
     * @param date
     * @return
     */
    public List<TicketDTO> selectTicketsByStationAndDate(@Param("startStationId") Long startStationId,
                                                         @Param("destStationId") Long destStationId,
                                                         @Param("date") String date);
    /**
     * 根据车次和开车日期筛选车票
     */
    public Tickets selectTicketsByIdAndDate(@Param("busId") String busId, @Param("startDate")Date startDate);
}
