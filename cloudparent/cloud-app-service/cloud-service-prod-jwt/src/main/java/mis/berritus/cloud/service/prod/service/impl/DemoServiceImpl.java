package mis.berritus.cloud.service.prod.service.impl;

import mis.berritus.cloud.app.bean.prod.SysProduct;
import mis.berritus.cloud.service.prod.dao.SysProductMapper;
import mis.berritus.cloud.service.prod.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class DemoServiceImpl implements DemoService {
    @Autowired
    private SysProductMapper sysProductMapper;

    @Override
    public SysProduct addTbSysProduct(SysProduct product) {
        product.setCrtDate(new Date());
        sysProductMapper.insert(product);
        return product;
    }
}
