package com.ruoyi.web.controller.system;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.model.SignUpBody;
import com.ruoyi.framework.web.service.SysSignUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册账号的接口
 */
@RestController
public class SysSignUpController {

    @Autowired
    SysSignUpService signUpService;

    /**
     * 注册功能接口
     * @param signUpBody 注册信息体
     * @return
     */
    @PostMapping("/signup/phone")
    public AjaxResult signUp(@RequestBody SignUpBody signUpBody) {
        signUpService.signUp(signUpBody.getPhone(), signUpBody.getPassword(), signUpBody.getUsername());
        return AjaxResult.success();
    }

}
