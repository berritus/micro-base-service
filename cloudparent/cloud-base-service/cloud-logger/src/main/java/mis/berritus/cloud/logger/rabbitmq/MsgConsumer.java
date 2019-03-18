package mis.berritus.cloud.logger.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import mis.berritus.cloud.bean.logger.SysRunLog;
import mis.berritus.cloud.logger.service.ILogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

import static mis.berritus.cloud.common.constant.RabbitMQConstant.ORDER_QUEUE_ONE;

@Component
public class MsgConsumer {
    private Logger logger = LoggerFactory.getLogger(MsgConsumer.class);

    public final String SYS_LOG_QUEUE = "sys_log_queue";
    private final String ORDER_QUEUE_TWO = "order_queue2";

    @Autowired
    private ILogService logService;

    @RabbitListener(queues = ORDER_QUEUE_TWO)
    @RabbitHandler
    public void handleCustMsg(Message message, Channel channel) throws IOException {
        try{
//            YfyCust cust = JSON.parseObject(message.getBody(), YfyCust.class);
//            System.out.println("handle order : " + cust.toString());
        }catch (Exception e){
            logger.error("处理失败，" + e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,true);
        }
    }

    @RabbitListener(queues = SYS_LOG_QUEUE)
    @RabbitHandler
    public void handleLogMsg(Message message, Channel channel) throws IOException {
        try{
            logger.warn("消息处理，队列" + SYS_LOG_QUEUE + "，数据：" + message.getBody());
            SysRunLog order = JSON.parseObject(message.getBody(), SysRunLog.class);
            System.out.println("handle order : " + order.toString());
            logService.insertSysRunLog(order);
        }catch (Exception e){
            /**
             * void basicNack(long deliveryTag, boolean multiple, boolean requeue) hrows IOException;
             * deliveryTag:该消息的index
             * multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
             * requeue：被拒绝的是否重新入队列
             */
            logger.error("消息处理失败，队列" + SYS_LOG_QUEUE + "，原因：" + e.getMessage());
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
        }
    }
}
