package com.ruoyi.bus.service;

import com.ruoyi.bus.domain.dto.VerifyNumberDTO;

/**
 * 司机端service接口
 * @author Zhenxi Chen
 * @date 2021/6/5
 */
public interface IDriverService {
    /**
     * 查看当前车次的已核销人数
     *
     * @param busId 车次
     * @return 已核销人数
     */
    int getCurrVerifyCount(String busId);

    /**
     * 查看当前车次的总乘客数
     *
     * @param busId 车次
     * @return
     */
    int getCurrCount(String busId);

    /**
     * 查看当前车次的总乘客数、已核销人数和未核销人数
     *
     * @param busId 车次
     * @return
     */
    VerifyNumberDTO getVerifyCount(String busId);
}
