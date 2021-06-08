package com.ruoyi.orders.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.SecurityUtils;
import com.ruoyi.orders.domain.OrderInfo;
import com.ruoyi.orders.domain.OrdersInfo;
import com.ruoyi.orders.domain.dto.SellCodeDTO;
import com.ruoyi.orders.domain.dto.UserOrdersDTO;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.orders.domain.Orders;
import com.ruoyi.orders.service.IOrdersService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 订单Controller
 * 
 * @author ruoyi
 * @date 2021-06-06
 */
@RestController
@RequestMapping({"/orders/orders","/ticketMiniProgram/orders"})
public class OrdersController extends BaseController
{
    @Autowired
    private IOrdersService ordersService;

    @Autowired
    private ISysUserService userService;

    /**
     * 查询订单列表
     */
    @PreAuthorize("@ss.hasPermi('orders:orders:list')")
    @GetMapping("/list")
    public TableDataInfo list(Orders orders)
    {
        startPage();
        List<Orders> list = ordersService.selectOrdersList(orders);
        return getDataTable(list);
    }

    /**
     * 导出订单列表
     */
    @PreAuthorize("@ss.hasPermi('orders:orders:export')")
    @Log(title = "订单", businessType = BusinessType.EXPORT)
    @GetMapping("/export")
    public AjaxResult export(Orders orders)
    {
        List<Orders> list = ordersService.selectOrdersList(orders);
        ExcelUtil<Orders> util = new ExcelUtil<Orders>(Orders.class);
        return util.exportExcel(list, "订单数据");
    }

    /**
     * 获取订单详细信息
     */
    @PreAuthorize("@ss.hasPermi('orders:orders:query')")
    @GetMapping(value = "/{orderId}")
    public AjaxResult getInfo(@PathVariable("orderId") String orderId)
    {
        return AjaxResult.success(ordersService.selectOrdersById(orderId));
    }

    /**
     * 新增订单
     */
    @PreAuthorize("@ss.hasPermi('orders:orders:add')")
    @Log(title = "订单", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody Orders orders)
    {
        return toAjax(ordersService.insertOrders(orders));
    }

    /**
     * 修改订单
     */
    @PreAuthorize("@ss.hasPermi('orders:orders:edit')")
    @Log(title = "订单", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody Orders orders)
    {
        return toAjax(ordersService.updateOrders(orders));
    }

    /**
     * 删除订单
     */
    @PreAuthorize("@ss.hasPermi('orders:orders:remove')")
    @Log(title = "订单", businessType = BusinessType.DELETE)
	@DeleteMapping("/{orderIds}")
    public AjaxResult remove(@PathVariable String[] orderIds)
    {
        return toAjax(ordersService.deleteOrdersByIds(orderIds));
    }

    /**
    * @Description 获取给定订单号的核销码的接口
    * @param orderID 订单编号
    * @return 结果
    * @author Mei Huang
    * @date 2021/6/6
    */
    @GetMapping("/sellcode")
    public AjaxResult sellCode(@RequestParam("orderID") String orderID){
        SellCodeDTO dto = ordersService.getSellCode(orderID);
        AjaxResult ajax = AjaxResult.success("success",dto);
        return ajax;
    }

    /**
    * @Description 获取用户订单列表
    * @param orderInfo
    * @return 结果
    * @author Mei Huang
    * @date 2021/6/8
    */
    @PostMapping("/list")
    public AjaxResult userOrderList(@RequestBody OrderInfo orderInfo) throws ParseException {
        System.out.println("orderInfo:"+orderInfo);

        String userName = SecurityUtils.getUsername();
        SysUser user = userService.selectUserByUserName(userName);
        Long userId = user.getUserId();
        Long userRoleId = user.getRoles().get(0).getRoleId();

        System.out.println("userRoleId:"+userRoleId);

        OrdersInfo userOrder = new OrdersInfo();
        userOrder.setUserId(userId);
        userOrder.setOrderID(orderInfo.getOrderID());
        userOrder.setStart(orderInfo.getStart());
        userOrder.setDest(orderInfo.getDest());
        Date date = null;
        if(orderInfo.getDate()!=null){
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");//注意月份是MM
            date = simpleDateFormat.parse(orderInfo.getDate());
        }
        userOrder.setDate(date);

        List<UserOrdersDTO> userOrdersList = null;
        if(userRoleId==2){
            //用户
            userOrdersList = ordersService.selectNormalOrdersList(userOrder);
        }else if(userRoleId==100||userRoleId==101){
            //员工
            userOrdersList = ordersService.selectEmployeeOrdersList(userOrder);
        }

        AjaxResult ajaxResult = AjaxResult.success();
        ajaxResult.put("length",userOrdersList.size());
        ajaxResult.put("orderList",userOrdersList);
        return ajaxResult;
    }
}
