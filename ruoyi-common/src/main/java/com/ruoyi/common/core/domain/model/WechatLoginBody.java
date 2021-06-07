package com.ruoyi.common.core.domain.model;

/**
 * 用于读取微信快捷登录的请求
 *
 * @author Zhenxi Chen
 * @date 2021/6/7
 */
public class WechatLoginBody {

    private String openid;      // 微信openid

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }
}
