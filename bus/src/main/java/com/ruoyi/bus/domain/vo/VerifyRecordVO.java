package com.ruoyi.bus.domain.vo;

import com.ruoyi.bus.domain.dto.VerifyDTO;

import java.util.List;

/**
 * 用作核销记录的展示对象
 * @author Zhenxi Chen
 * @date 2021/6/6
 */
public class VerifyRecordVO {
    private int length;     // 记录数组长度

    private List<VerifyDTO> verifyList;     // 存储核销记录的list

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public List<VerifyDTO> getVerifyList() {
        return verifyList;
    }

    public void setVerifyList(List<VerifyDTO> verifyList) {
        this.verifyList = verifyList;
    }
}
