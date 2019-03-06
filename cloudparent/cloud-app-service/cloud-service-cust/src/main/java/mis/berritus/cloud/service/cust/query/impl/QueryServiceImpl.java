package mis.berritus.cloud.service.cust.query.impl;

import mis.berritus.cloud.bean.service.cust.MisCustBase;
import mis.berritus.cloud.service.cust.dao.MisCustBaseMapper;
import mis.berritus.cloud.service.cust.query.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueryServiceImpl implements QueryService {
    @Autowired
    private MisCustBaseMapper misCustBaseMapper;

    @Override
    public MisCustBase qryMisCustBase(Integer custId) {
        return misCustBaseMapper.selectByPrimaryKey(custId);
    }
}
