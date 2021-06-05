package com.ruoyi.bus.domain.dto;

/**
 * 用于向前端传递核验票人数数据的DTO
 * @author Zhenxi Chen
 * @date 2021/6/5
 */
public class VerifyNumberDTO {
    private String busID;       // 车次号

    private int totalNum;       // 总核销人数

    private int verifyNum;      // 已核销人数

    private int unVerifyNum;    // 未核销人数

    public VerifyNumberDTO(String busID, int totalNum, int verifyNum, int unVerifyNum) {
        this.busID = busID;
        this.totalNum = totalNum;
        this.verifyNum = verifyNum;
        this.unVerifyNum = unVerifyNum;
    }

    public VerifyNumberDTO() {

    }

    public String getBusID() {
        return busID;
    }

    public void setBusID(String busID) {
        this.busID = busID;
    }

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getVerifyNum() {
        return verifyNum;
    }

    public void setVerifyNum(int verifyNum) {
        this.verifyNum = verifyNum;
    }

    public int getUnVerifyNum() {
        return unVerifyNum;
    }

    public void setUnVerifyNum(int unVerifyNum) {
        this.unVerifyNum = unVerifyNum;
    }
}
