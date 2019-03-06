package mis.berritus.cloud.service.cust.service.impl;

import mis.berritus.cloud.bean.service.cust.MisCustBase;
import mis.berritus.cloud.service.cust.dao.MisCustBaseMapper;
import mis.berritus.cloud.service.cust.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private MisCustBaseMapper misCustBaseMapper;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer insertCustBase(MisCustBase misCustBase) {
        misCustBase.setCrtDate(new Date());
        misCustBase.setStateDate(new Date());
        misCustBase.setState((byte)0);
        misCustBaseMapper.insert(misCustBase);
        return misCustBase.getCustId();
    }
}
