package com.ruoyi.common.exception.user;

/**
 * 微信openid未与账号绑定异常类
 */
public class WechatNotBindException extends UserException {

    public WechatNotBindException() {
        super("user.wechat.not.bind", null);
    }
}
