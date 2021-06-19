package com.ruoyi.orders.constant;

/**
 * 订单状态对应的
 */
public class OrderStatusConstants {

    public static final String UNPAID = "0";        // 未支付

    public static final String UNVERIFIED = "1";        // 待核销

    public static final String VERIFIED = "2";      // 已核销

    public static final String CLOSED = "3";        // 已关闭

    public static final int NORMAL = 0;         // 普通票

    public static final int EMPLOYEE = 1;       // 员工票
}
