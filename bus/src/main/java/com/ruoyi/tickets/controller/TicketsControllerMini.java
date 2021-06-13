package com.ruoyi.tickets.controller;

import java.util.List;

import com.ruoyi.bus.domain.OrderId;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.domain.model.LoginUser;
import com.ruoyi.framework.web.service.TokenService;
import com.ruoyi.tickets.domain.DTO.TicketDTO;
import com.ruoyi.tickets.domain.TicketOrder;
import com.ruoyi.tickets.service.ITicketsExtendService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.tickets.domain.Tickets;
import com.ruoyi.tickets.service.ITicketsService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import javax.servlet.http.HttpServletRequest;
/**
 * 车票管理Controller
 *
 * @author ruoyi
 * @date 2021-06-07
 */
@RestController
@RequestMapping("/ticket")
public class TicketsControllerMini
{
    @Autowired
    private ITicketsExtendService ticketsService;
    @Autowired
    private TokenService tokenService;
    /**
     * 返回符合对应查询条件的车票
     * @param start 出发车站id
     * @param dest
     * @param date
     * @param ticketType
     * @param date2
     * @return
     */
    @GetMapping("/bus/list")
    public AjaxResult ticketList(@RequestParam("start") String start,
                                 @RequestParam("dest") String dest,
                                 @RequestParam("date") String date,
                                 @RequestParam("ticketType") int ticketType,
                                 @RequestParam("date2") String date2) {
        if (start==null||dest==null||date==null){
            return AjaxResult.error("缺少必要参数");
        }
        List<TicketDTO> ticketList=null;
        if (ticketType==0){
            //查询单程
            ticketList=ticketsService.queryTickets(start,dest,date);
        }else if(ticketType==1){
            if(date2==null){
                return AjaxResult.error("缺少返程日期");
            }
            //查询往返并拼接
            List<TicketDTO> ticketListGo=ticketsService.queryTickets(start,dest,date);
            List<TicketDTO> ticketListBack=ticketsService.queryTickets(dest,start,date2);
            ticketList=ticketListGo;
            ticketList.addAll(ticketListBack);
        }else{
            return AjaxResult.error("无法解析车票类型");
        }
        AjaxResult result = AjaxResult.success();
        System.out.println(ticketList);
        result.put("length", ticketList.size());
        result.put("busList", ticketList);
        return result;
    }

    /**
     * 返回符合对应查询条件的车票
     * @return
     */
    @PostMapping("/purchase")
    public AjaxResult ticketList(HttpServletRequest request,@RequestBody TicketOrder ticketOrder) {
        SysUser user = tokenService.getLoginUser(request).getUser();
        Long userId=user.getUserId();
        Long userRole=user.getRoles().get(0).getRoleId();
        String orderId=ticketsService.purchaseTicket(ticketOrder,userId,userRole);
        AjaxResult result;
        if (orderId==null){
            result= AjaxResult.error("购票失败");
        }else{
            result = AjaxResult.success();
            result.put("orderId",orderId);
        }
        return result;

    }

    /**
     * 返回符合对应查询条件的车票
     * @return
     */
    @GetMapping("/payOrder")
    public AjaxResult payOrder(HttpServletRequest request,@RequestParam("orderId") String orderId) {
        SysUser user = tokenService.getLoginUser(request).getUser();
        Long userId=user.getUserId();
        Long userRole=user.getRoles().get(0).getRoleId();
        boolean isPaySuccess=ticketsService.payOrder(orderId,userId,userRole);
        if (isPaySuccess){
            return AjaxResult.success();
        }else{
            return AjaxResult.error("付款失败");
        }
    }

}

