package com.ruoyi.bus.service;

import com.ruoyi.bus.domain.dto.VerifyNumberDTO;
import com.ruoyi.bus.domain.vo.TodayBusVO;
import com.ruoyi.bus.domain.vo.VerifyRecordVO;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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

    /**
     * 校验当前订单的状态是否为待核验且为今日订单
     *
     * @param orderId 订单号
     * @return bool值 true表示状态为待核验 false表示无效或状态错误
     */
    boolean checkOrderStatus(String orderId);

    /**
     * 查询核销记录
     *
     * @param busId 车次
     * @return 核销记录的list（将长度信息单独封装后组成VO）
     */
    VerifyRecordVO verifyRecord(String busId);

    /**
     * 查询该司机今日的车次
     *
     * @param request HTTP请求信息
     * @return
     */
    List<TodayBusVO> todayBus(HttpServletRequest request);

    /**
     * 将订单状态修改为已核验
     * 并通知前端已进行校验
     *
     * @param orderId 订单号
     */
    void verifyTicket(String orderId);
}
