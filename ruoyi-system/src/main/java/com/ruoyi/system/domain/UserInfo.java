package com.ruoyi.system.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 用户信息补充对象 user_info
 * 
 * @author ruoyi
 * @date 2021-06-04
 */
public class UserInfo extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 用户ID */
    private Long userId;

    /** 用户真名 */
    @Excel(name = "用户真名")
    private String name;

    /** 用户身份证号 */
    @Excel(name = "用户身份证号")
    private String idCard;

    /** 用户绑定的微信openid */
    @Excel(name = "用户绑定的微信openid")
    private String openId;

    /** 余额 */
    @Excel(name = "余额")
    private Long balance;

    public void setUserId(Long userId) 
    {
        this.userId = userId;
    }

    public Long getUserId() 
    {
        return userId;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setIdCard(String idCard) 
    {
        this.idCard = idCard;
    }

    public String getIdCard() 
    {
        return idCard;
    }
    public void setOpenId(String openId) 
    {
        this.openId = openId;
    }

    public String getOpenId() 
    {
        return openId;
    }
    public void setBalance(Long balance) 
    {
        this.balance = balance;
    }

    public Long getBalance() 
    {
        return balance;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("userId", getUserId())
            .append("name", getName())
            .append("idCard", getIdCard())
            .append("openId", getOpenId())
            .append("balance", getBalance())
            .toString();
    }
}
