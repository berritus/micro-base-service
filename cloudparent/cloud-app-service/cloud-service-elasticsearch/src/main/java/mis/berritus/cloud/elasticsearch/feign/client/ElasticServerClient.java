package mis.berritus.cloud.elasticsearch.feign.client;

import mis.berritus.cloud.app.bean.elasticsearch.SearchDTO;
import mis.berritus.cloud.elasticsearch.feign.client.conf.DemoFeignConfig;
import mis.berritus.cloud.elasticsearch.feign.client.fallback.ElasticServerClientFallback;
import mis.berritus.cloud.elasticsearch.feign.client.fallback.SysServiceClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/3
 */
@FeignClient(value = "localhost:9200", fallback = ElasticServerClientFallback.class, configuration = DemoFeignConfig.class)
public interface ElasticServerClient {

    @PostMapping("/cust_base/customer/_search")
    String getData(@RequestBody SearchDTO searchDTO);
}
