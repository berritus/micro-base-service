package mis.berritus.cloud.service.cust.service.impl;

import mis.berritus.cloud.app.bean.elasticsearch.MisCustBaseExt;
import mis.berritus.cloud.bean.service.cust.MisCustBase;
import mis.berritus.cloud.service.cust.dao.MisCustBaseMapper;
import mis.berritus.cloud.service.cust.feign.client.ElasticSearchClient;
import mis.berritus.cloud.service.cust.service.DemoService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.UUID;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private MisCustBaseMapper misCustBaseMapper;
    @Autowired
    private ElasticSearchClient elasticSearchClient;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public Integer insertCustBase(MisCustBase misCustBase) {

        if (StringUtils.isEmpty(misCustBase.getCustId())) {
            String uuid = UUID.randomUUID().toString();
            misCustBase.setCustId(uuid);
        }

        misCustBase.setCrtDate(new Date());
        misCustBase.setStateDate(new Date());
        misCustBase.setState((byte)0);
        misCustBaseMapper.insert(misCustBase);

        MisCustBaseExt misCustBaseExt = new MisCustBaseExt();
        BeanUtils.copyProperties(misCustBase, misCustBaseExt);
        misCustBaseExt.setEsIndex("cust_base");
        misCustBaseExt.setEsType("customer");
        elasticSearchClient.insertMisCustBase(misCustBaseExt);
        return 0;
    }
}
