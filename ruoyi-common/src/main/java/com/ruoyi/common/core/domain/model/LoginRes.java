package com.ruoyi.common.core.domain.model;

public class LoginRes {
    private String token;       // 返回给前端的JWT

    private String type;        // 返回给前端的用户类型

    public LoginRes(String token, String type) {
        this.token = token;
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
