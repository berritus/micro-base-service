package com.berritus.cloud.message.dao;

import mis.berritus.cloud.bean.message.ConfMessageMq;
import mis.berritus.cloud.dao.MisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfMessageMqMapper extends MisDao<ConfMessageMq, Long> {
    List<ConfMessageMq> listConfMessageMqs(ConfMessageMq sysMqMsg);
}