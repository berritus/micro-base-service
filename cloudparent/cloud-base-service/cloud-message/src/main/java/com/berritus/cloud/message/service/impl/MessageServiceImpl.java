package com.berritus.cloud.message.service.impl;

import com.alibaba.fastjson.JSON;
import com.berritus.cloud.message.component.ExChangeConfig;
import com.berritus.cloud.message.component.QueueConfig;
import com.berritus.cloud.message.component.RabbitMQConfig;
import com.berritus.cloud.message.dao.TbSysMqMsgMapper;
import com.berritus.cloud.message.service.MessageService;
import com.rabbitmq.client.Channel;
import mis.berritus.cloud.bean.logger.SysRunLog;
import mis.berritus.cloud.bean.message.RequestMsg;
import mis.berritus.cloud.bean.message.TbSysMqMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.util.Date;
import java.util.UUID;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageServiceImpl.class);

    @Autowired
    private TbSysMqMsgMapper sysMqMsgMapper;
    @Autowired
    //@Qualifier("msgRabbitTemplate")
    private RabbitTemplate rabbitTemplate;

    @Override
    public int insertSysMqMsg(TbSysMqMsg record) {
        record.setCrtDate(new Date());
        record.setStateDate(new Date());
        sysMqMsgMapper.insert(record);
        return record.getMsgId();
    }

    @Override
    public void updateSysMqMsg(TbSysMqMsg record){
        record.setStateDate(new Date());
        sysMqMsgMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int sendConfirmMsg(RequestMsg requestMsg){
        TbSysMqMsg msg = sysMqMsgMapper.selectByPrimaryKey(requestMsg.getMsgId());
        if(msg.getState() != 1){
            return 0;
        }

        // 构建回调返回的数据
        String corId = UUID.randomUUID().toString().replace("-", "");
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(corId);

        Message message = MessageBuilder.withBody(msg.getSendMsg().getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                // 将CorrelationData的id 与 Message的correlationId绑定，然后关系保存起来,然后人工处理
                .setCorrelationId(correlationData.getId())
                .setMessageId(UUID.randomUUID().toString().replace("-", "")).build();

        rabbitTemplate.convertAndSend(msg.getExChange(), msg.getRoutingKey(), message, correlationData);

        TbSysMqMsg record = new TbSysMqMsg();
        record.setMsgId(requestMsg.getMsgId());
        record.setState(2);
        record.setStateDate(new Date());
        sysMqMsgMapper.updateByPrimaryKeySelective(record);

        return 0;
    }

    @Override
    public int testSysMqMsg() {
        String corId = UUID.randomUUID().toString().replace("-", "");
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(corId);

        TbSysMqMsg record = new TbSysMqMsg();
        record.setStateDate(new Date());
        record.setState(1000);
        record.setCrtDate(new Date());
        record.setEndDate(new Date());
        record.setMsgId(10000);
        record.setMsgCode("40000");
        record.setExChange("test_exchange");
        record.setRoutingKey("test_routing_key");
        record.setSendMsg("hello world");
        String json = JSON.toJSONString(record);
        Message message = MessageBuilder.withBody(json.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setMessageId(UUID.randomUUID().toString().replace("-", ""))
                .setCorrelationId(correlationData.getId()).build();

        rabbitTemplate.convertAndSend(record.getExChange(), record.getRoutingKey(), message, correlationData);
        return 0;
    }

    @Override
    public int testSysMqMsg2() {
        String corId = UUID.randomUUID().toString().replace("-", "");
        CorrelationData correlationData = new CorrelationData();
        correlationData.setId(corId);

        TbSysMqMsg record = new TbSysMqMsg();
        record.setStateDate(new Date());
        record.setState(1000);
        record.setCrtDate(new Date());
        record.setEndDate(new Date());
        record.setMsgId(10000);
        record.setMsgCode("40000");
        record.setExChange("test_exchange");
        record.setRoutingKey("test_routing_key2");
        record.setSendMsg("hello world2");
        String json = JSON.toJSONString(record);
        Message message = MessageBuilder.withBody(json.getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setMessageId(UUID.randomUUID().toString().replace("-", ""))
                .setCorrelationId(correlationData.getId()).build();

        rabbitTemplate.convertAndSend(record.getExChange(), record.getRoutingKey(), message, correlationData);
        return 0;
    }


    @RabbitListener(queues = "test_queue")
    @RabbitHandler
    public void handleLogMsg(Message message, Channel channel) throws IOException {
        try{
            logger.warn("消息处理，队列" + QueueConfig.ORDER_QUEUE_ONE + "，数据：" + message.getBody());
            TbSysMqMsg order = JSON.parseObject(message.getBody(), TbSysMqMsg.class);
            System.out.println("handle order : " + order.toString());
        }catch (Exception e){
            /**
             * void basicNack(long deliveryTag, boolean multiple, boolean requeue) hrows IOException;
             * deliveryTag:该消息的index
             * multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
             * requeue：被拒绝的是否重新入队列
             */
            logger.error("消息处理失败，队列" + QueueConfig.ORDER_QUEUE_ONE + "，原因：" + e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
        }
    }

    @RabbitListener(queues = "test_queue2")
    @RabbitHandler
    public void handleLogMsg2(Message message, Channel channel) throws IOException {
        try{
            logger.warn("消息处理，队列" + QueueConfig.ORDER_QUEUE_ONE + "，数据：" + message.getBody());
            TbSysMqMsg order = JSON.parseObject(message.getBody(), TbSysMqMsg.class);
            System.out.println("handle order : " + order.toString());
        }catch (Exception e){
            /**
             * void basicNack(long deliveryTag, boolean multiple, boolean requeue) hrows IOException;
             * deliveryTag:该消息的index
             * multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
             * requeue：被拒绝的是否重新入队列
             */
            logger.error("消息处理失败，队列" + QueueConfig.ORDER_QUEUE_ONE + "，原因：" + e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
        }
    }
}
