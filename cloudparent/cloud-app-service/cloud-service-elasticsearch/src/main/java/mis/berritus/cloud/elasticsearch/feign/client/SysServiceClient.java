package mis.berritus.cloud.elasticsearch.feign.client;

import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.elasticsearch.feign.client.conf.DemoFeignConfig;
import mis.berritus.cloud.elasticsearch.feign.client.fallback.SysServiceClientFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
@FeignClient(value = "cloud-sys-service", fallback = SysServiceClientFallback.class, configuration = DemoFeignConfig.class)
public interface SysServiceClient {

    @GetMapping("/sys/getSysParam")
    SystemParam getSystemParam(@RequestParam("paramCode") String paramCode);
}
