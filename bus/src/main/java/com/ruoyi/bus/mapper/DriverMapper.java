package com.ruoyi.bus.mapper;

/**
 * 司机端功能所需要进行的SQL操作对应的mapper接口
 * @author Zhenxi Chen
 * @date 2021/6/5
 */
public interface DriverMapper {
    /**
     * 查看当前车次的已核销人数
     * @param busId 司机ID
     * @return 已核销人数
     */
    int selectCurrVerifyCount(String busId);

    /**
     * 查看当前车次的总乘客数
     * @param busId
     * @return
     */
    int selectCurrCount(String busId);
}
