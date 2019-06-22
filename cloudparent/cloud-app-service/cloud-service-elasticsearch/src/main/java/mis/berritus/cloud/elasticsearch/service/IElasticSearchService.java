package mis.berritus.cloud.elasticsearch.service;

import mis.berritus.cloud.app.bean.elasticsearch.ElasticsearchRespone;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
public interface IElasticSearchService {
    String getAllIndexs();

    ElasticsearchRespone createIndex(String indexName);

    ElasticsearchRespone deleteIndex(String indexName);
}
