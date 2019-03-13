package com.berritus.cloud.message.service;

import mis.berritus.cloud.bean.message.RequestMsg;
import mis.berritus.cloud.bean.message.TbSysMqMsg;
import org.springframework.web.bind.annotation.RequestBody;

public interface MessageService {
    int insertSysMqMsg(TbSysMqMsg record);
    void updateSysMqMsg(TbSysMqMsg record);
    int sendConfirmMsg(@RequestBody RequestMsg requestMsg);
}
