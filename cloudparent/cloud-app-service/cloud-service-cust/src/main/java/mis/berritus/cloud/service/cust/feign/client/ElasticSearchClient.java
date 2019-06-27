package mis.berritus.cloud.service.cust.feign.client;

import mis.berritus.cloud.app.bean.elasticsearch.MisCustBaseExt;
import mis.berritus.cloud.service.cust.feign.client.conf.DemoFeignConfig;
import mis.berritus.cloud.service.cust.feign.client.fallback.ElasticSearchClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/28
 */
@FeignClient(value = "cloud-service-elasticsearch", fallback = ElasticSearchClientFallback.class, configuration = DemoFeignConfig.class)
public interface ElasticSearchClient {

    @PostMapping("/es/cust/insertMisCustBase")
    MisCustBaseExt insertMisCustBase(@RequestBody MisCustBaseExt misCustBaseExt);
}
