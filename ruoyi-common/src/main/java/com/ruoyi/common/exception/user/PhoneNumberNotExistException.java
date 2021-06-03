package com.ruoyi.common.exception.user;

/**
 * 手机号码未注册异常类
 *
 * @author Zhenxi Chen
 * @date 2021/6/3
 */
public class PhoneNumberNotExistException extends UserException {
    private static final long serialVersionUID = 1L;

    public PhoneNumberNotExistException() {
        super("user.phone.not.exist", null);
    }
}
