package com.ruoyi.bus.controller;

import com.ruoyi.bus.domain.dto.VerifyNumberDTO;
import com.ruoyi.bus.service.IDriverService;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.TokenUtils;
import com.ruoyi.framework.web.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

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


}
