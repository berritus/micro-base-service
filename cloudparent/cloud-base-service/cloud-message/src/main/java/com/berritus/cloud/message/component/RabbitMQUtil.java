package com.berritus.cloud.message.component;

import mis.berritus.cloud.bean.message.MisMessageRoute;
import mis.berritus.cloud.common.constant.MessageConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Binding;

import java.util.UUID;

@Component
public class RabbitMQUtil {
    private static final Logger logger = LoggerFactory.getLogger(RabbitMQUtil.class);

    @Autowired
    private RabbitAdmin rabbitAdmin;
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * @Description: 发送消息
     * @Date: 2020/1/19 0:17
     * @Author: Qin Guihe
     */
    public void sendMessage(Message message, String routingKey) {
        MisMessageRoute misMessageRoute = new MisMessageRoute();
        misMessageRoute.setExchangeName(MessageConstant.DEFAULT_EXCHANGE);
        misMessageRoute.setRoutintKey(routingKey);
        sendMessage(message, misMessageRoute);
    }

    /**
      * @Description: 发送消息
      * @Date: 2020/1/19 0:17
      * @Author: Qin Guihe
      */
    public void sendMessage(Message message, String exchangeName, String routingKey) {
        MisMessageRoute misMessageRoute = new MisMessageRoute();
        misMessageRoute.setExchangeName(exchangeName);
        misMessageRoute.setRoutintKey(routingKey);
        sendMessage(message, misMessageRoute);
    }

    /**
      * @Description:  发送消息
      * @Date: 2020/1/19 0:06
      * @Author: Qin Guihe
      *
      */
    public void sendMessage(Message message, MisMessageRoute misMessageRoute) {
        // 构建回调返回的数据
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(message.getMessageProperties().getCorrelationId());

        rabbitTemplate.convertAndSend(misMessageRoute.getExchangeName(), misMessageRoute.getRoutintKey(),
                message, correlationData);
    }

    /**
      * @Description: 生成消息体
      * @Date: 2020/1/19 0:06
      * @Author: Qin Guihe
      */
    public Message getMessage(Object msg){
        return getMessage(msg, MessageProperties.CONTENT_TYPE_JSON);
    }

    /**
      * @Description:
      * @Date: 2020/1/19 0:06
      * @Author: Qin Guihe
      */
    public Message getMessage(Object msg, String messageType){
        String correlationId = UUID.randomUUID().toString().replace("-", "");

        Message message = MessageBuilder.withBody(msg.toString().getBytes())
                .setContentType(messageType)
                // 将CorrelationData的id 与 Message的correlationId绑定，然后关系保存起来,然后人工处理
                .setCorrelationId(correlationId)
                .setMessageId(UUID.randomUUID().toString().replace("-", "")).build();

        return message;
    }

    /**
      * @Description:
      * @Date: 2020/1/19 0:07
      * @Author: Qin Guihe
      */
    public boolean deleteExchange(String exchangeName){
        logger.info("删除rabbit交换机名：{}", exchangeName);
        return rabbitAdmin.deleteExchange(exchangeName);
    }

    /**
      * @Description:
      * @Date: 2020/1/19 0:07
      * @Author: Qin Guihe
      */
    public boolean deleteQueue(String queueName){
        logger.info("删除rabbit队列名：{}", queueName);
        return rabbitAdmin.deleteQueue(queueName);
    }

    /**
      * @Description:
      * @Date: 2020/1/19 0:07
      * @Author: Qin Guihe
      */
    public void removeBinding(Binding binding){
        logger.info("删除rabbit绑定routing key：{}", binding.getRoutingKey());
        rabbitAdmin.removeBinding(binding);
    }
}
