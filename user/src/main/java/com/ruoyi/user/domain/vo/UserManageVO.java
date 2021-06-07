package com.ruoyi.user.domain.vo;

import com.ruoyi.common.annotation.Excel;

public class UserManageVO {

    /**
     * 用户ID
     */
    @Excel(name = "用户ID", cellType = Excel.ColumnType.NUMERIC, prompt = "用户ID")
    private Long userId;

    /**
     * 用户名
     */
    @Excel(name = "用户名", cellType = Excel.ColumnType.STRING, prompt = "用户名")
    private String username;

    /**
     * 角色
     */
    @Excel(name = "用户角色", cellType = Excel.ColumnType.STRING, prompt = "用户角色")
    private String role;

    /**
     * 手机号码
     */
    @Excel(name = "手机号码")
    private String phonenumber;

    /**
     * 余额
     */
    private int balance;

    public boolean isAdmin() {
        return isAdmin(this.userId);
    }

    public static boolean isAdmin(Long userId) {
        return userId != null && 1L == userId;
    }

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
