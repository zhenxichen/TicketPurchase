package com.ruoyi.tickets.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.station.service.IStationExtendService;
import com.ruoyi.tickets.domain.DTO.TicketDTO;
import com.ruoyi.tickets.domain.TicketOrder;
import com.ruoyi.tickets.domain.Tickets;
import com.ruoyi.tickets.mapper.TicketsExtendMapper;
import com.ruoyi.tickets.mapper.TicketsMapper;
import com.ruoyi.tickets.service.ITicketsExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class ITicketsExtendServiceImpl implements ITicketsExtendService {

    @Autowired
    private TicketsExtendMapper ticketsExtendMapper;

    @Autowired
    private TicketsMapper ticketsMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private IStationExtendService stationService;

    @Override
    public List<TicketDTO> queryTickets(String start, String dest, String date) {
        Long startStationId=stationService.selectStationByName(start).getStationId();
        Long destStationId=stationService.selectStationByName(dest).getStationId();



        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate=null;
        try {
            startDate=sdf.parse(date);
            System.out.println(sdf.format(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<TicketDTO> ticketList=ticketsExtendMapper.selectTicketsByStationAndDate(startStationId,destStationId,sdf.format(startDate));
        return ticketList;
    }

    @Override
    public String purchaseTicket(TicketOrder ticketOrder,Long uerId,Long userRole) {
        Tickets ticket=ticketsExtendMapper.selectTicketsByIdAndDate(ticketOrder.getBusId(),ticketOrder.getStartDate());
        Long employeeTicketsRemain=ticket.getEmployeeTicketsRemain();
        Long normalTicketsRemain=ticket.getNormalTicketsRemain();
        if (userRole==2){
            if(normalTicketsRemain>0){

            }else{
                return null;
            }
        }else{
            if(employeeTicketsRemain>0){

            }else if(normalTicketsRemain>0){

            }else{
                return null;
            }
        }
        //LoginUser loginUser = tokenService.getLoginUser(request);
        //TicketOrder ticketOrder=request.get
        return null;
    }
}
