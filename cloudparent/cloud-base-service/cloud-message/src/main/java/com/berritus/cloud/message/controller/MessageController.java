package com.berritus.cloud.message.controller;

import com.berritus.cloud.message.service.MessageService;
import mis.berritus.cloud.bean.message.TbSysMqMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {
    @Autowired
    private MessageService messageService;

    // http://localhost:8106/
    @GetMapping("")
    public String index(){
        messageService.testSysMqMsg();
        messageService.testSysMqMsg2();
        return "this is message system";
    }

    // http://localhost:8106//msg/add
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
