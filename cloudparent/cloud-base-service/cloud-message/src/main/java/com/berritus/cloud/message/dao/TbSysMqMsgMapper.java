package com.berritus.cloud.message.dao;

import mis.berritus.cloud.bean.message.TbSysMqMsg;
import com.berritus.mis.core.dao.MisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbSysMqMsgMapper extends MisDao<TbSysMqMsg, Integer> {
    List<TbSysMqMsg> qrySysMqMsg(TbSysMqMsg sysMqMsg);
}