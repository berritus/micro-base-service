package com.berritus.cloud.message.controller;


import com.berritus.cloud.message.service.MessageService;
import mis.berritus.cloud.bean.message.RequestMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageSender {

    @Autowired
    private MessageService messageService;

    @RequestMapping(method = RequestMethod.POST, value = "/msg/confirm")
    public int sendConfirmMsg(@RequestBody RequestMsg requestMsg){
        try{
            return messageService.sendConfirmMsg(requestMsg);
        }catch (Exception e){
            return -1;
        }
    }
}
