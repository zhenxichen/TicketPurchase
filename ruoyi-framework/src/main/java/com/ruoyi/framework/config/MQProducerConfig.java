package com.ruoyi.framework.config;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息队列消费者配置类
 *
 * @author Zhenxi Chen
 * @date 2021/6/17
 */
@Configuration
public class MQProducerConfig {

    private static final Logger log = LoggerFactory.getLogger(MQProducerConfig.class);

    @Value("${rocketmq.producer.groupName}")
    private String groupName;       // group名称

    @Value("${rocketmq.producer.namesrvAddr}")
    private String namesrvAddr;     // name server地址

    @Value("${rocketmq.producer.maxMessageSize}")
    private Integer maxMessageSize ;        // 最大消息体大小

    @Value("${rocketmq.producer.sendMsgTimeout}")
    private Integer sendMsgTimeout;         // time out

    @Value("${rocketmq.producer.retryTimesWhenSendFailed}")
    private Integer retryTimesWhenSendFailed;       // 重试次数

    @Bean
    public DefaultMQProducer defaultMQProducer() {
        DefaultMQProducer producer = new DefaultMQProducer(this.groupName);
        producer.setNamesrvAddr(namesrvAddr);
        producer.setMaxMessageSize(maxMessageSize);
        producer.setSendMsgTimeout(sendMsgTimeout);
        producer.setRetryTimesWhenSendFailed(retryTimesWhenSendFailed);
        try {
            producer.start();
            log.info("Producer is started.");
        } catch (Exception e) {
            log.error("Failed to start producer", e);
        }
        return producer;
    }
}
