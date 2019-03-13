package com.berritus.cloud.message.service.impl;

import com.berritus.cloud.message.dao.TbSysMqMsgMapper;
import com.berritus.cloud.message.service.MessageService;
import mis.berritus.cloud.bean.message.RequestMsg;
import mis.berritus.cloud.bean.message.TbSysMqMsg;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;

@Service
@Transactional
public class MessageServiceImpl implements MessageService {
    @Autowired
    private TbSysMqMsgMapper sysMqMsgMapper;
    @Autowired
    @Qualifier("msgRabbitTemplate")
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

        Message message = MessageBuilder.withBody(msg.getSendMsg().getBytes())
                .setContentType(MessageProperties.CONTENT_TYPE_JSON)
                .setCorrelationId(requestMsg.getCode()).build();

        CorrelationData correlationData = new CorrelationData(msg.getMsgCode());
        rabbitTemplate.convertAndSend(msg.getExChange(), msg.getRoutingKey(), message, correlationData);

        TbSysMqMsg record = new TbSysMqMsg();
        record.setMsgId(requestMsg.getMsgId());
        record.setState(2);
        record.setStateDate(new Date());
        sysMqMsgMapper.updateByPrimaryKeySelective(record);

        return 0;
    }
}
