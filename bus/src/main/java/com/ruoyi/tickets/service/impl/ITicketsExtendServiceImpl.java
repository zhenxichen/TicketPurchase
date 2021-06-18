package com.ruoyi.tickets.service.impl;

import com.ruoyi.bus.service.IBusService;
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
import com.ruoyi.tickets.domain.dao.BusIdAndDateDAO;
import com.ruoyi.tickets.mapper.TicketsExtendMapper;
import com.ruoyi.tickets.mapper.TicketsManageMapper;
import com.ruoyi.tickets.mapper.TicketsMapper;
import com.ruoyi.tickets.service.ITicketsExtendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private TicketsManageMapper ticketsManageMapper;

    @Autowired
    private IUserInfoService iuserInfoService;

    @Autowired
    private IBusService iBusService;

    private static final int NORMAL_TICKET = 0;

    private static final int EMPLOYEE_TICKET = 1;

    /**
     * 根据条件查询车票
     *
     * @param start 始发站名称
     * @param dest  终点站名称
     * @param date  日期
     * @return
     */
    @Override
    public List<TicketDTO> queryTickets(String start, String dest, String date) {
        Long startStationId = stationService.selectStationByName(start).getStationId();
        Long destStationId = stationService.selectStationByName(dest).getStationId();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = sdf.parse(date);
            System.out.println(sdf.format(startDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        List<TicketDTO> ticketList = ticketsExtendMapper.selectTicketsByStationAndDate(startStationId, destStationId, sdf.format(startDate));
        return ticketList;
    }

    /**
     * 购票，返回订单id。订单状态未支付
     * 员工优先买员工票，没有员工票可以买普通票
     * 普通用户只能买普通用户
     * 购票成功返回订单id
     * 购票失败返回null
     * 目前防错机制不太完善
     *
     * @param ticketOrder
     * @return
     */
    @Override
    public String purchaseTicket(TicketOrder ticketOrder, Long userId, Long userRole) {
        //选择当天的车次
        Tickets ticket = ticketsExtendMapper.selectTicketsByIdAndDate(ticketOrder.getBusId(), ticketOrder.getStartDate());
        //余票
        Long employeeTicketsRemain = ticket.getEmployeeTicketsRemain();
        Long normalTicketsRemain = ticket.getNormalTicketsRemain();
        //创建订单
        String orderId = new OrderIdCreator(userId, ticketOrder.getBusId(), ticketOrder.getStartDate()).createOrderId();
        Orders orders = new Orders();
        orders.setBus(ticketOrder.getBusId());
        orders.setDate(ticketOrder.getStartDate());
        orders.setOrderId(orderId);
        orders.setUserId(userId);
        orders.setStatus("0");
        orders.setCreateTime(new Date());


        //购票
        if (userRole == 2) {
            if (normalTicketsRemain > 0) {
                orders.setType(NORMAL_TICKET);          // 设置
                orders.setPrice(ticket.getNormalPrice());
                ordersMapper.insertOrders(orders);
                ticket.setNormalTicketsRemain(ticket.getNormalTicketsRemain() - 1);

            } else {
                return null;
            }
        } else {
            orders.setPrice(ticket.getEmployeePrice());
            if (employeeTicketsRemain > 0) {    //优先买员工票
                orders.setType(EMPLOYEE_TICKET);
                ordersMapper.insertOrders(orders);
                ticket.setEmployeeTicketsRemain(ticket.getEmployeeTicketsRemain() - 1);
            } else if (normalTicketsRemain > 0) {   //没有员工篇买普通票，但是价格还是员工价
                orders.setType(NORMAL_TICKET);
                ordersMapper.insertOrders(orders);
                ticket.setNormalTicketsRemain(ticket.getNormalTicketsRemain() - 1);
            } else {
                return null;
            }
        }
        //更新票余量
        ticketsExtendMapper.updateTickets(ticket);
        return orderId;
    }

    /**
     * 付款
     */
    @Override
    public boolean payOrder(String orderId, Long userId, Long userRole) {
        UserInfo userinfo = iuserInfoService.selectUserInfoById(userId);
        Orders order = ordersMapper.selectOrdersById(orderId);
        System.out.println(order.getStatus());
        if (userinfo.getBalance() < order.getPrice() || order.getStatus().equals("1")) {
            return false;
        }
        userinfo.setBalance(userinfo.getBalance() - order.getPrice());
        order.setStatus("1");
        if (ordersMapper.updateOrders(order) == 0 || iuserInfoService.updateUserInfo(userinfo) == 0) {
            return false;
        }
        return true;
    }

    /**
     * 退票
     *
     * @param orderId 订单号
     * @return
     */
    @Override
    public boolean refundOrder(String orderId) {

        Orders order = ordersMapper.selectOrdersById(orderId);
        if (order.getStatus().equals("0")) {
            order.setStatus("3");
        } else if (order.getStatus().equals("1")) {
            order.setStatus("3");

            UserInfo userinfo = iuserInfoService.selectUserInfoById(order.getUserId());
            if (userinfo == null) {
                return false;
            }
            userinfo.setBalance(userinfo.getBalance() + order.getPrice());
            if (iuserInfoService.updateUserInfo(userinfo) == 0) {
                return false;
            }
        } else {
            return false;
        }
        Tickets tickets=ticketsManageMapper.selectTicketsByIdAndDate(new BusIdAndDateDAO(order.getBus(),order.getDate()));
        if (order.getType()==1){
            tickets.setEmployeeTicketsRemain(tickets.getEmployeeTicketsRemain()+1);
        }else{
            tickets.setNormalTicketsRemain(tickets.getNormalTicketsRemain()+1);
        }
        if(ordersMapper.updateOrders(order)==0||ticketsMapper.updateTickets(tickets)==0){
            return false;
        }
        return true;
    }
}
