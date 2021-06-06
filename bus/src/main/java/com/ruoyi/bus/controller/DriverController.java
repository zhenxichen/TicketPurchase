package com.ruoyi.bus.controller;

import com.ruoyi.bus.domain.OrderId;
import com.ruoyi.bus.domain.dto.VerifyDTO;
import com.ruoyi.bus.domain.dto.VerifyNumberDTO;
import com.ruoyi.bus.domain.vo.TodayBusVO;
import com.ruoyi.bus.domain.vo.VerifyRecordVO;
import com.ruoyi.bus.service.IDriverService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.TokenUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 实现司机端API的接口
 * @author Zhenxi Chen
 * @date 2021/6/5
 */
@RestController
@RequestMapping("/driver")
public class DriverController {

    private final String SUCCESS_MSG = "success";

    @Autowired
    IDriverService driverService;

    /**
     * 获取当前车次验票人数的接口
     *
     * @param busID 车次号
     * @return
     */
    @PreAuthorize("@ss.hasPermi('api:driver:verifynumber')")
    @GetMapping("/trainIVerify/verifyNum")
    public AjaxResult verifyNumber(@RequestParam("busID") String busID) {
        VerifyNumberDTO dto = driverService.getVerifyCount(busID);
        AjaxResult ajax = AjaxResult.success(SUCCESS_MSG, dto);
        return ajax;
    }

    /**
     * 进行车票信息核验的接口
     *
     * @param orderId
     * @return
     */
    @PreAuthorize("@ss.hasPermi('api:driver:trainiverify')")
    @PostMapping("/trainIVerify")
    public AjaxResult verify(@RequestBody OrderId orderId) {
        if (driverService.checkOrderStatus(orderId.getOrderID())) {
            driverService.verifyTicket(orderId.getOrderID());
            return AjaxResult.success();
        }
        return AjaxResult.error("订单信息错误");
    }

    /**
     * 查看核销记录的接口
     *
     * @param busID 车次
     * @return
     */
    @PreAuthorize("@ss.hasPermi('api:driver:verifyrecord')")
    @GetMapping("/trainVerify")
    public AjaxResult verify(@RequestParam("busID") String busID) {
        VerifyRecordVO vo = driverService.verifyRecord(busID);
        System.out.println(vo.getVerifyList().get(0).getVerifyTime());
        return AjaxResult.success(vo);
    }


    /**
     * 查看司机今日的班车列表
     *
     * @param request HTTP请求
     * @return
     */
    @PreAuthorize("@ss.hasPermi('api:driver:buslist')")
    @GetMapping("/bus/list")
    public AjaxResult busList(HttpServletRequest request) {
        List<TodayBusVO> list = driverService.todayBus(request);
        AjaxResult result = AjaxResult.success();
        result.put("length", list.size());
        result.put("busList", list);
        return result;
    }
}
