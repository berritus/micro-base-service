package mis.berritus.cloud.service.cust.controller;

import mis.berritus.cloud.bean.service.cust.MisCustBase;
import mis.berritus.cloud.service.cust.query.QueryService;
import mis.berritus.cloud.service.cust.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class CustDemoController {
    @Value("${application.name}")
    private String applicationName;
    @Autowired
    private DemoService demoService;
    @Autowired
    private QueryService queryService;

    @GetMapping("/")
    public String index(){
        return this.applicationName;
    }

    //http://localhost:8105/cust/add
    @PostMapping("/cust/add")
    public Integer insertCustBase(@RequestBody MisCustBase misCustBase){
        return demoService.insertCustBase(misCustBase);
    }

    //http://localhost:8105/cust/qry?custId=100011
    @GetMapping("/cust/qry")
    public MisCustBase qryMisCustBase(Integer custId){
        return queryService.qryMisCustBase(custId);
    }
}
