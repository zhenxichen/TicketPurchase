package com.ruoyi.bus.mapper;

import com.ruoyi.bus.domain.dto.TodayBusDTO;
import com.ruoyi.bus.domain.dto.VerifyDTO;

import java.util.List;

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

    /**
     * 校验当前车票的状态是否为待核销且为今日订单
     * @param orderId 订单号
     * @return
     */
    int checkOrderStatus(String orderId);

    /**
     * 查询指定车次的核销记录
     * @param busId 车次
     * @return
     */
    List<VerifyDTO> selectVerifyRecord(String busId);

    /**
     * 查询该司机当日的车次列表
     * @param driver 司机的用户名
     * @return
     */
    List<TodayBusDTO> selectTodayBus(String driver);

    /**
     * 将订单状态更新为已核验（已完成）
     * @param orderId 订单号
     * @return
     */
    int updateStatusToVerified(String orderId);
}
