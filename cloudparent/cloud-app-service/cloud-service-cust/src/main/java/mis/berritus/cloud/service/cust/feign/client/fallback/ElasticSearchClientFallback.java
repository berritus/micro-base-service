package mis.berritus.cloud.service.cust.feign.client.fallback;

import mis.berritus.cloud.app.bean.elasticsearch.MisCustBaseExt;
import mis.berritus.cloud.service.cust.feign.client.ElasticSearchClient;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/28
 */
@Component
public class ElasticSearchClientFallback implements ElasticSearchClient {
    @Override
    public MisCustBaseExt insertMisCustBase(MisCustBaseExt misCustBaseExt) {
        return null;
    }
}
