package com.berritus.cloud.message.controller;

import com.berritus.cloud.message.service.MessageService;
import mis.berritus.cloud.bean.message.TbSysMqMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping("/")
    public String index(){
        return "this is message system";
    }

    @RequestMapping(method = RequestMethod.POST, value = "/msg/add")
    public int addMsg(@RequestBody TbSysMqMsg record){
        try{
            System.out.println("service");
            return messageService.insertSysMqMsg(record);
        }catch (Exception e){
            return -1;
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/msg/update")
    public void updateSysMqMsg(TbSysMqMsg record){
        messageService.updateSysMqMsg(record);
    }
}
