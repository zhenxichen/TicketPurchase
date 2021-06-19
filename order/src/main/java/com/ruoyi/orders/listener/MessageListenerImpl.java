package com.ruoyi.orders.listener;

import com.ruoyi.orders.constant.MQConstants;
import com.ruoyi.orders.constant.OrderStatusConstants;
import com.ruoyi.orders.domain.Orders;
import com.ruoyi.orders.service.IOrderStatusService;
import com.ruoyi.orders.service.IOrdersService;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * 消息处理类
 *
 * @author Zhenxi Chen
 * @date 2021/6/17
 */
@Component
public class MessageListenerImpl implements MessageListenerConcurrently {

    private static final Logger log = LoggerFactory.getLogger(MessageListenerImpl.class);

    @Autowired
    private IOrderStatusService orderStatusService;

    @Autowired
    private IOrdersService ordersService;

    @Override
    public ConsumeConcurrentlyStatus consumeMessage(
            List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
        if (CollectionUtils.isEmpty(list)) {    // 若没有待消费的消息
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        }
        MessageExt message = list.get(0);
        String msg = new String(message.getBody());
        log.info("Consume a message." + msg);
        switch (message.getTags()) {
            case MQConstants.ORDER_TIME_OUT_TAG: {
                Orders order = ordersService.selectOrdersById(msg);
                if (order.getStatus().equals(OrderStatusConstants.UNPAID)) {
                    orderStatusService.closeOrder(order);
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
            default: {
                log.error("Unknown tag: " + message.getTags());
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        }
    }
}
