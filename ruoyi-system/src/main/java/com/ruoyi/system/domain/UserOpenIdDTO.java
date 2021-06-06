package com.ruoyi.system.domain;

public class UserOpenIdDTO {
    private String userId;      // 用户ID

    private String username;    // 用户名

    private String openId;      // open ID

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }
}
