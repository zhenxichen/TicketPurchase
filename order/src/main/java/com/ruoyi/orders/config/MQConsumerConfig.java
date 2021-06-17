package com.ruoyi.orders.config;

import com.ruoyi.orders.listener.MessageListenerImpl;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * RocketMQ消费者配置
 *
 * @author Zhenxi Chen
 * @date 2021/6/17
 */
@Configuration
public class MQConsumerConfig {

    private static final Logger log = LoggerFactory.getLogger(MQConsumerConfig.class);

    @Value("${rocketmq.consumer.groupName}")
    private String groupName;

    @Value("${rocketmq.consumer.namesrvAddr}")
    private String namesrvAddr;

    @Value("${rocketmq.consumer.topics}")
    private String topics;

    @Value("${rocketmq.consumer.consumeThreadMin}")
    private Integer consumeThreadMin;

    @Value("${rocketmq.consumer.consumeThreadMax}")
    private Integer consumeThreadMax;

    @Value("${rocketmq.consumer.consumeMessageBatchMaxSize}")
    private Integer consumeMessageBatchMaxSize;

    @Autowired
    private MessageListenerImpl messageListener;

    @Bean
    public DefaultMQPushConsumer defaultMQPushConsumer() {
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer(groupName);
        consumer.setNamesrvAddr(namesrvAddr);
        consumer.setConsumeThreadMin(consumeThreadMin);
        consumer.setConsumeThreadMax(consumeThreadMax);
        consumer.setConsumeMessageBatchMaxSize(consumeMessageBatchMaxSize);
        consumer.registerMessageListener(messageListener);
        try {
            consumer.subscribe(topics, "*");
            consumer.start();
            log.info("Consumer is started.");
        } catch (Exception e) {
            log.error("Failed to start consumer", e);
        }
        return consumer;
    }


}
