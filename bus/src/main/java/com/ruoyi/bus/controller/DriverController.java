package com.ruoyi.bus.controller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 实现司机端API的接口
 * @author Zhenxi Chen
 * @date 2021/6/5
 */
@RestController
@RequestMapping("/driver")
public class DriverController {

    @PreAuthorize("api:driver:verify")
    @GetMapping()
    public AjaxResult verifyNumber() {
        return null;
    }
}
