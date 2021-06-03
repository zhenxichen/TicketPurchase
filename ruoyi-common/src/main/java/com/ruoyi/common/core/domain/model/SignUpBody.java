package com.ruoyi.common.core.domain.model;

/**
 * 用户注册对象
 *
 * @author Zhenxi Chen
 * @date 2021/6/3
 */
public class SignUpBody {
    private String phone;       // 手机号

    private String password;    // 密码

    private String username;    // 用户名

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
