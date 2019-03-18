package com.berritus.cloud.message.task;

import com.berritus.cloud.message.feign.client.CustServerClient;
import com.berritus.cloud.message.service.MessageService;
import com.berritus.cloud.message.service.QryMessageService;
import com.berritus.mis.core.cache.redis.RedisService;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.base.Page;
import mis.berritus.cloud.bean.message.TbSysMqMsg;
import mis.berritus.cloud.common.constant.MessageConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Component
public class MessageTask {
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private RedisService redisService;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private QryMessageService qryMessageService;
    @Autowired
    private MessageService messageService;
    //@Autowired
    //private CustServerClient custServerClient;

    @Scheduled(fixedRate = 60000)
    public void reportCurrentTime(){
        boolean flag = false;

        try{
            //上锁
            flag = redisTemplate.opsForValue().setIfAbsent(MessageConstant.MESSAGE_TASK_KEY, MessageConstant.MESSAGE_TASK_VALUE);
            if(!flag){
                System.out.println("return task");
                return;
            }

            System.out.println("running task");
            long startTime = System.currentTimeMillis();
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.MINUTE, -30);
            TbSysMqMsg bean = new TbSysMqMsg();
            bean.setState(1);
            bean.setEndDate(calendar.getTime());
            Page page = new Page();
            page.setPageNum(1);
            page.setPageSize(500);
            PageInfo<TbSysMqMsg> plist = qryMessageService.qrySysMqMsg(bean, page);
            List<TbSysMqMsg> list = plist.getList();
            if(list == null || list.size() == 0){
                System.out.println("handle total " + list.size());
                throw new RuntimeException("handle total " + list.size());
            }

            System.out.println("handle total " + list.size());
            for(TbSysMqMsg sysMqMsg : list){
//                TbSysMqMsg bean2 = new TbSysMqMsg();
//                BankAccountReq req = custServerClient.qryBankAccountReqByMsgId(sysMqMsg.getMsgId());
//                if(req == null){
//                    bean2.setState(0);
//                }else {
//                    bean2.setState(3);
//                }
//
//                bean2.setMsgId(sysMqMsg.getMsgId());
//                messageService.updateSysMqMsg(bean2);
            }

            long endTime = System.currentTimeMillis();
            System.out.println("use times " + (endTime - startTime));
        } catch(Exception e) {

        } finally{
            if(flag){
                redisTemplate.delete(MessageConstant.MESSAGE_TASK_KEY);
            }
        }
    }
}
