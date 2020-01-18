package com.berritus.cloud.message.component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Binding;

@Component
public class RabbitMQUtil {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQUtil.class);

    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public boolean deleteExchange(String exchangeName){
        logger.info("删除rabbit交换机名：{}", exchangeName);
        return rabbitAdmin.deleteExchange(exchangeName);
    }

    public boolean deleteQueue(String queueName){
        logger.info("删除rabbit队列名：{}", queueName);
        return rabbitAdmin.deleteQueue(queueName);
    }

    public void removeBinding(Binding binding){
        logger.info("删除rabbit绑定routing key：{}", binding.getRoutingKey());
        rabbitAdmin.removeBinding(binding);
    }
}
