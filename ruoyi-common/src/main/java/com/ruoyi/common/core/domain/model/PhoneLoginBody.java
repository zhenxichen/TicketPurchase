package com.ruoyi.common.core.domain.model;

/**
 * 用户手机登录对象
 *
 * @author Zhenxi Chen
 * @date 2021/6/3
 */
public class PhoneLoginBody {

    private String phone;   // 用户手机号

    private String password;        // 用户密码

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
}
