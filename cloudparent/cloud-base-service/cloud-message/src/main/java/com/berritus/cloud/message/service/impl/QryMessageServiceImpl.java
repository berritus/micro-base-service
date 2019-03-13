package com.berritus.cloud.message.service.impl;

import com.berritus.cloud.message.dao.TbSysMqMsgMapper;
import com.berritus.cloud.message.service.QryMessageService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import mis.berritus.cloud.bean.base.Page;
import mis.berritus.cloud.bean.message.TbSysMqMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QryMessageServiceImpl implements QryMessageService {
    private Logger logger = LoggerFactory.getLogger(QryMessageServiceImpl.class);

    @Autowired
    private TbSysMqMsgMapper sysMqMsgMapper;

    @Override
    public PageInfo<TbSysMqMsg> qrySysMqMsg(TbSysMqMsg sysMqMsg, Page page) {
        PageHelper.startPage(page.getPageNum(), page.getPageSize());
        List<TbSysMqMsg> list = sysMqMsgMapper.qrySysMqMsg(sysMqMsg);
        PageInfo<TbSysMqMsg> pageInfo = new PageInfo<TbSysMqMsg>(list);
        return pageInfo;
    }
}
