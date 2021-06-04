package com.ruoyi.framework.web.service;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.user.SignUpException;
import com.ruoyi.system.domain.UserInfo;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 注册方法
 *
 * @author Zhenxi Chen
 * @date 2021/6/3
 */
@Component
public class SysSignUpService {

    @Autowired
    private ISysUserService userService;

    @Autowired
    private IUserInfoService infoService;

    /**
     * 进行账号注册
     * @param phoneNumber 手机号
     * @param password 密码
     * @param username 用户名
     */
    public void signUp(String phoneNumber, String password, String username) {
        SysUser user = new SysUser();
        user.setPhonenumber(phoneNumber);
        user.setUserName(username);
        user.setPassword(password);
        user.setNickName(username);     // 由于RuoYi自带的用户表结构必须填写nickname，因此使用username代替
        checkSignUpInfoValid(user);     // 校验用户名和手机号是否重复
        userService.insertUser(user);
        user = userService.selectUserByUserName(username);
        UserInfo info = new UserInfo();
        info.setUserId(user.getUserId());
        infoService.insertUserInfo(info);
    }

    /**
     * 校验注册信息是否合法
     * @param user SysUser对象
     * @return 是否合法
     */
    private boolean checkSignUpInfoValid(SysUser user) {
        if (UserConstants.NOT_UNIQUE.equals(userService.checkUserNameUnique(user.getUserName()))) {
            throw new SignUpException(SignUpException.USERNAME_EXISTED);
        }
        if (UserConstants.NOT_UNIQUE.equals(userService.checkPhoneUnique(user))) {
            throw new SignUpException(SignUpException.PHONE_NUMBER_EXISTED);
        }
        return true;
    }
}
