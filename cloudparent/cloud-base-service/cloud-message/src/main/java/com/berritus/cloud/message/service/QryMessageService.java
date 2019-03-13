package com.berritus.cloud.message.service;

import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.base.Page;
import mis.berritus.cloud.bean.message.TbSysMqMsg;

public interface QryMessageService {
    PageInfo<TbSysMqMsg> qrySysMqMsg(TbSysMqMsg sysMqMsg, Page page);
}
