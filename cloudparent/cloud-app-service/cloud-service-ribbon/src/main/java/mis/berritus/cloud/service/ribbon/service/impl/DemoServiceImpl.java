package mis.berritus.cloud.service.ribbon.service.impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import mis.berritus.cloud.bean.service.cust.MisCustBase;
import mis.berritus.cloud.service.ribbon.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class DemoServiceImpl implements DemoService{
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Integer insertCustBase(MisCustBase misCustBase) {
        return null;
    }

    @HystrixCommand(fallbackMethod = "helloFallBack")
    @Override
    public MisCustBase qryMisCustBase(Integer custId) {
        return restTemplate.getForObject("http://cloud-service-cust/cust/qry?custId=" + custId, MisCustBase.class);
    }

    public MisCustBase helloFallBack(Integer custId){
        return new MisCustBase();
    }
}
