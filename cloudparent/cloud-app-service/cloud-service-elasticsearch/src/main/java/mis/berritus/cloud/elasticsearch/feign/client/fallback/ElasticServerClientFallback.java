package mis.berritus.cloud.elasticsearch.feign.client.fallback;

import mis.berritus.cloud.app.bean.elasticsearch.SearchDTO;
import mis.berritus.cloud.elasticsearch.feign.client.ElasticServerClient;
import org.springframework.stereotype.Component;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/7/3
 */
@Component
public class ElasticServerClientFallback implements ElasticServerClient {
    @Override
    public String getData(SearchDTO searchDTO) {
        return null;
    }
}
