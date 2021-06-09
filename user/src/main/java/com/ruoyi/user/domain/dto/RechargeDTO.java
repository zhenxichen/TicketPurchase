package com.ruoyi.user.domain.dto;

/**
 * 用于进行用户充值的DTO
 * @author Zhenxi Chen
 * @date 2021/6/9
 */
public class RechargeDTO {
    private Long[] userIds;

    private int amount;

    public Long[] getUserIds() {
        return userIds;
    }

    public void setUserIds(Long[] userIds) {
        this.userIds = userIds;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
