package com.ruoyi.quartz.task;

import com.ruoyi.orders.service.IOrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * 用于进行关闭订单的定时任务
 *
 * @author Zhenxi Chen
 * @date 2021/6/15
 */
@Component("closeOrderTask")
public class CloseOrderTask {
    @Autowired
    private IOrdersService ordersService;

    /**
     * 关闭日期早于今日且状态为未核销的订单
     */
    public void closeBeforeOrder() {
        ordersService.closeOrdersBeforeToday();
    }
}
