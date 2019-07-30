package mis.berritus.cloud.elasticsearch.service;

import mis.berritus.cloud.app.bean.elasticsearch.ElasticsearchRespone;
import mis.berritus.cloud.app.bean.elasticsearch.MisCustBaseExt;

import java.util.List;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
public interface IElasticSearchService {
    String getAllIndexs();

    /**
     * @Description: 创建索引
     * @Date: 2019/7/16
     * @Author: Qin Guihe
     *
     */
    Integer createIndex(String indexName);

    Integer deleteIndex(String indexName);

    MisCustBaseExt insertMisCustBase(MisCustBaseExt misCustBaseExt);

    List<ElasticsearchRespone> listMisCustBases(MisCustBaseExt misCustBaseExt);

    /**
     * @Description: 新建文档（数据源json串）
     * 当直接在ElasticSearch建立文档对象时，如果索引不存在的，默认会自动创建，映射采用默认方式。
     * @Date: 2019/7/16
     * @Author: Qin Guihe
     *
     */
    Integer createIndexByJson(String indexName, String indexType, String json);

    Integer insertIndexByJson(String indexName, String indexType, String json);
}
