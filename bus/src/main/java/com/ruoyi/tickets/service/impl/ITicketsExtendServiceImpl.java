package com.ruoyi.tickets.service.impl;

import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.orders.mapper.OrdersMapper;
import com.ruoyi.orders.util.OrderIdCreator;
import com.ruoyi.orders.domain.Orders;
import com.ruoyi.station.service.IStationExtendService;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.service.IUserInfoService;
import com.ruoyi.tickets.domain.DTO.TicketDTO;
import com.ruoyi.tickets.domain.TicketOrder;
import com.ruoyi.tickets.domain.Tickets;
import com.ruoyi.tickets.mapper.TicketsExtendMapper;
import com.ruoyi.tickets.mapper.TicketsMapper;
import com.ruoyi.tickets.service.ITicketsExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
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
    private OrdersMapper ordersMapper;
    @Autowired
    private TokenService tokenService;

    @Autowired
    private IStationExtendService stationService;

    @Autowired
    private IUserInfoService iuserInfoService;

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
    public String purchaseTicket(TicketOrder ticketOrder,Long userId,Long userRole) {
        //选择当天的车次
        Tickets ticket=ticketsExtendMapper.selectTicketsByIdAndDate(ticketOrder.getBusId(),ticketOrder.getStartDate());
        //余票
        Long employeeTicketsRemain=ticket.getEmployeeTicketsRemain();
        Long normalTicketsRemain=ticket.getNormalTicketsRemain();
        //创建订单
        String orderId= new OrderIdCreator(userId,ticketOrder.getBusId(),ticketOrder.getStartDate()).createOrderId();
        Orders orders=new Orders();
        orders.setBus(ticketOrder.getBusId());
        orders.setDate(ticketOrder.getStartDate());
        orders.setOrderId(orderId);
        orders.setUserId(userId);
        orders.setStatus("1");
        orders.setCreateTime(new Date());

        UserInfo userinfo=iuserInfoService.selectUserInfoById(userId);
        Long balance=userinfo.getBalance();//获取用户余额

        //购票
        if (userRole==2){
            if(normalTicketsRemain>0){
                if(balance<ticket.getNormalPrice()){
                    return null;
                }
                userinfo.setBalance(userinfo.getBalance()-ticket.getNormalPrice());//用正常价更新余额
                ordersMapper.insertOrders(orders);
                ticket.setNormalTicketsRemain(ticket.getNormalTicketsRemain()-1);

            }else{
                return null;
            }
        }else{
            //先判断余额是否够员工票


            if(balance<ticket.getEmployeePrice()){
                return null;
            }
            userinfo.setBalance(userinfo.getBalance()-ticket.getEmployeePrice());//用员工价更新余额
            if(employeeTicketsRemain>0){//优先买员工篇
                ordersMapper.insertOrders(orders);
                ticket.setEmployeeTicketsRemain(ticket.getEmployeeTicketsRemain()-1);
            }else if(normalTicketsRemain>0){//没有员工篇买普通票，但是价格还是员工价
                ordersMapper.insertOrders(orders);
                ticket.setNormalTicketsRemain(ticket.getNormalTicketsRemain()-1);
            }else{
                return null;
            }
        }
        //更新余额
        iuserInfoService.updateUserInfo(userinfo);
        //更新票余量
        ticketsExtendMapper.updateTickets(ticket);
        //LoginUser loginUser = tokenService.getLoginUser(request);
        //TicketOrder ticketOrder=request.get
        return orderId;
    }
}
