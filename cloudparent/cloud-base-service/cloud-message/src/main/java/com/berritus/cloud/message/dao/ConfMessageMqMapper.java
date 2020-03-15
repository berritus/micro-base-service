package com.berritus.cloud.message.dao;

import mis.berritus.cloud.bean.message.ConfMessageMq;
import com.berritus.mis.core.dao.MisDao;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConfMessageMqMapper extends MisDao<ConfMessageMq, Long> {
    List<ConfMessageMq> listConfMessageMqs(ConfMessageMq sysMqMsg);
}