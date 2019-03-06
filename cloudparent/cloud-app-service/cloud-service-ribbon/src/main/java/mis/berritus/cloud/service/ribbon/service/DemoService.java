package mis.berritus.cloud.service.ribbon.service;

import mis.berritus.cloud.bean.service.cust.MisCustBase;

public interface DemoService {
    Integer insertCustBase(MisCustBase misCustBase);

    MisCustBase qryMisCustBase(Integer custId);
}
