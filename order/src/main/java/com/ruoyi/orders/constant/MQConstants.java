package com.ruoyi.orders.constant;

/**
 * 消息队列机制中使用到的常数
 */
public class MQConstants {

    public static final String ORDER_TOPIC = "order-pay";   // 订单支付相关topic名称

    // 订单超时未支付的TAG
    public static final String ORDER_TIME_OUT_TAG = "order-overtime";

    // 延迟一分钟对应的延迟级别
    public static final int DELAY_LEVEL_OF_1MIN_DELAY = 5;

    // 延时10分钟对应的延迟级别
    public static final int DELAY_LEVEL_OF_10MIN_DELAY = 14;
}
