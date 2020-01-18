package com.berritus.cloud.message.dao;

import mis.berritus.cloud.bean.message.ConfExchangeMq;
import mis.berritus.cloud.dao.MisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfExchangeMqMapper extends MisDao<ConfExchangeMq, Long> {

    List<ConfExchangeMq> qryConfExchangeMq(ConfExchangeMq sysMqMsg);
}