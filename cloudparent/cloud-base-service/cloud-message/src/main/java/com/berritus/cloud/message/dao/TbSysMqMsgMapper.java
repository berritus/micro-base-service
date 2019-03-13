package com.berritus.cloud.message.dao;

import mis.berritus.cloud.bean.message.TbSysMqMsg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TbSysMqMsgMapper {
    int deleteByPrimaryKey(Integer msgId);

    int insert(TbSysMqMsg record);

    int insertSelective(TbSysMqMsg record);

    TbSysMqMsg selectByPrimaryKey(Integer msgId);

    int updateByPrimaryKeySelective(TbSysMqMsg record);

    int updateByPrimaryKey(TbSysMqMsg record);

    List<TbSysMqMsg> qrySysMqMsg(TbSysMqMsg sysMqMsg);
}