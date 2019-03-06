package mis.berritus.cloud.service.ribbon.controller;

import mis.berritus.cloud.bean.service.cust.MisCustBase;
import mis.berritus.cloud.service.ribbon.service.DemoService;
import mis.berritus.cloud.service.ribbon.service.impl.DemoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {
    @Autowired
    private DemoService demoService;

    @GetMapping("/cust/qry")
    public MisCustBase qryMisCustBase(Integer custId){
        return demoService.qryMisCustBase(custId);
    }

}
