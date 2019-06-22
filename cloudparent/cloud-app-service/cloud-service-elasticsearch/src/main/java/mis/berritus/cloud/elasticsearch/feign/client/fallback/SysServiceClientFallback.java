package mis.berritus.cloud.elasticsearch.feign.client.fallback;

import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.elasticsearch.feign.client.SysServiceClient;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
@Component
public class SysServiceClientFallback implements SysServiceClient {

    @Override
    public SystemParam getSystemParam(String paramCode) {
        return null;
    }
}
