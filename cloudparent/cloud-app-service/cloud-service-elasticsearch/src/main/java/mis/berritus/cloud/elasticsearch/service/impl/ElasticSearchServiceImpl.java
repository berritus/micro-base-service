package mis.berritus.cloud.elasticsearch.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.berritus.mis.core.cache.redis.IRedisService;
import com.berritus.mis.core.component.utils.HttpUtil;
import mis.berritus.cloud.app.bean.elasticsearch.ElasticsearchRespone;
import mis.berritus.cloud.app.bean.elasticsearch.MisCustBaseExt;
import mis.berritus.cloud.app.common.constant.SysConfigConstants;
import mis.berritus.cloud.bean.service.cust.MisCustBase;
import mis.berritus.cloud.bean.sys.service.SystemParam;
import mis.berritus.cloud.elasticsearch.feign.client.SysServiceClient;
import mis.berritus.cloud.elasticsearch.service.IElasticSearchCommon;
import mis.berritus.cloud.elasticsearch.service.IElasticSearchService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @Description:
 * @Copyright: mis520
 * @Author: Qin Guihe
 * @Date: 2019/6/22
 */
@Service
public class ElasticSearchServiceImpl implements IElasticSearchService {
    private String elasticSearchHost;

    @Autowired
    private SysServiceClient sysServiceClient;
    @Autowired
    private IRedisService redisService;
    @Autowired
    private IElasticSearchCommon elasticSearchCommon;

    @Override
    public String getAllIndexs() {
        String url = elasticSearchCommon.getParamValue(SysConfigConstants.ELASTIC_SEARCH_ALL_INDEXS_URL);

        String str = HttpUtil.get(url);
        return str;
    }

    @Override
    public ElasticsearchRespone createIndex(String indexName) {
        String url = elasticSearchCommon.getParamValue(SysConfigConstants.ELASTIC_SEARCH_HOST);

        String resultStr = HttpUtil.put(url + indexName);
        if (!StringUtils.isEmpty(resultStr)) {
            ElasticsearchRespone respone = JSON.parseObject(resultStr, ElasticsearchRespone.class);
            return respone;
        }

        return null;
    }

    @Override
    public ElasticsearchRespone deleteIndex(String indexName) {
        String url = elasticSearchCommon.getParamValue(SysConfigConstants.ELASTIC_SEARCH_HOST);

        String resultStr = HttpUtil.delete(url + indexName);
        if (!StringUtils.isEmpty(resultStr)) {
            ElasticsearchRespone respone = JSON.parseObject(resultStr, ElasticsearchRespone.class);
            return respone;
        }

        return null;
    }

    @Override
    public MisCustBaseExt insertMisCustBase(MisCustBaseExt misCustBaseExt) {
        if (misCustBaseExt == null) {
            return null;
        }

        MisCustBase misCustBase = new MisCustBase();

        BeanUtils.copyProperties(misCustBaseExt, misCustBase);

        String url = elasticSearchCommon.getParamValue(SysConfigConstants.ELASTIC_SEARCH_HOST);
        url += misCustBaseExt.getEsIndex() + "/" + misCustBaseExt.getEsType();

        String json = JSON.toJSONString(misCustBase);
        Map<String, String> params = (Map<String, String>)JSON.parse(json);
        String result = HttpUtil.post(url, params);

        return misCustBaseExt;
    }
}
