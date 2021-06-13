package com.ruoyi.user.domain.dto;

public class UserModifyDTO {

    private String phoneNumber;  // 用户电话号码

    private String pwd; //用户密码

    private String nickName;  //用户昵称

    private  String idCard ; //用户身份证

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) { this.pwd = pwd; }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) { this.nickName = nickName; }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }
}
