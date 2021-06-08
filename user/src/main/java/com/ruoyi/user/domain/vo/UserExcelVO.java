package com.ruoyi.user.domain.vo;

import com.ruoyi.common.annotation.Excel;

public class UserExcelVO {

    @Excel(name = "用户序号", cellType = Excel.ColumnType.NUMERIC, prompt = "用户编号")
    private Long userId;        // 用户ID

    @Excel(name = "用户名")
    private String username;    // 用户名

    @Excel(name = "用户昵称")
    private String nickName;    // 昵称

    @Excel(name = "手机号码")
    private String phonenumber; // 手机号

    @Excel(name = "用户角色", readConverterExp = "1=超级管理员,2=普通用户,100=员工,101=司机")
    private String role;        // 角色

    @Excel(name = "账号状态", readConverterExp = "0=正常,1=停用")
    private String status;      // 状态

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
