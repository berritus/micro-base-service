package com.berritus.cloud.message.component;

import com.berritus.cloud.message.dao.ConfExchangeMqMapper;
import com.berritus.cloud.message.dao.ConfMessageMqMapper;
import mis.berritus.cloud.bean.message.ConfExchangeMq;
import mis.berritus.cloud.bean.message.ConfMessageMq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import java.util.Collection;
import java.util.HashMap;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

@Component
public class MqConfigRegister implements ApplicationContextAware, ApplicationListener<ContextRefreshedEvent> {
    private static final Logger logger = LoggerFactory.getLogger(MqConfigRegister.class);
    private ApplicationContext applicationContext;

    @Autowired
    private RabbitAdmin rabbitAdmin;

    @Autowired
    private ConfExchangeMqMapper confExchangeMqMapper;
    @Autowired
    private ConfMessageMqMapper confMessageMqMapper;

    private static Map<String, DirectExchange> directExchangeHashMap = new ConcurrentHashMap<>();
    private static Map<String, Queue> queueHashMap = new ConcurrentHashMap<>();
    private static List<String> routingKeys = new CopyOnWriteArrayList<>();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
        if(contextRefreshedEvent.getApplicationContext() != this.applicationContext) {
            return;
        }

        ConfMessageMq confMessageMq = new ConfMessageMq();
        confMessageMq.setState((byte)1);
        List<ConfMessageMq> list = confMessageMqMapper.listConfMessageMqs(confMessageMq);
        if (CollectionUtils.isEmpty(list)) {
            return;
        }

        for (ConfMessageMq bean : list) {
            /*************exahange****************/
            boolean durable = true;
            boolean autoDelete = false;
            if (bean.getExchangeDurable() != null && bean.getExchangeDurable() == 0) {
                durable = false;
            }

            if (bean.getExchangeAutoDelete() != null && bean.getExchangeAutoDelete() == 1) {
                autoDelete = true;
            }

            DirectExchange exchange = directExchangeHashMap.get(bean.getExchangeName());
            if (exchange == null) {
                // DirectExchange exchange = new DirectExchange(confExchangeMq1.getExchangeName(), true, false);
                exchange = new DirectExchange(bean.getExchangeName(), durable, autoDelete);
                rabbitAdmin.declareExchange(exchange);
                directExchangeHashMap.put(bean.getExchangeName(), exchange);
            }

            /*************queue****************/
            durable = true;
            autoDelete = false;
            boolean exclusive = false;

            if (bean.getQueueDurable() != null && bean.getQueueDurable() == 0) {
                durable = false;
            }

            if (bean.getQueueAutoDelete() != null && bean.getQueueAutoDelete() == 1) {
                autoDelete = true;
            }

            if (bean.getQueueExclusive() != null && bean.getQueueExclusive() == 1) {
                exclusive = true;
            }

            Queue queue = queueHashMap.get(bean.getQueueName());
            if (queue == null) {
                // queue = new Queue(bean.getQueueName(), true, false, false);
                queue = new Queue(bean.getQueueName(), durable, exclusive, autoDelete);
                rabbitAdmin.declareQueue(queue);
            }

            /*************binding****************/
            if (routingKeys.contains(bean.getRoutintKey())) {
                continue;
            }
            // return BindingBuilder.bind(queueConfig.strMsgQueue()).to(exChangeConfig.msgExChange()).with(MSG_ROUTING_KEY);
            Binding binding = BindingBuilder.bind(queue).to(exchange).with(bean.getRoutintKey());
            rabbitAdmin.declareBinding(binding);
        }
    }
}
